#define BAUD 9600
#include <avr/io.h>
#include <util/delay.h>
#include <util/setbaud.h>
#include <avr/interrupt.h>

int main() {
 uint16_t adc_value, heartRate;
 uint16_t timerValue[5] = {750, 750, 750, 750, 750}; 
 uint16_t thresholdVoltage = 4;
 int counter = 0;
 
 initUSART();
 initADC();
 initTimer1();

 while(1) {
   
   adc_value = (readADC() * 5) / 1023;
   
   if(adc_value >= thresholdVoltage) {
     TCNT1 = 0;
     
     while(adc_value >= thresholdVoltage) {
       _delay_ms(1);
       adc_value = (readADC() * 5) / 1023;
     }
     
     while(adc_value <= 1) {
       _delay_ms(1);
       adc_value = (readADC() * 5) / 1023;
     }
     
     timerValue[counter] = (TCNT1 >> 4); 
     
     if(counter < 5) {
       counter++; 
     }
     else {
       counter = 0; 
     }
     
     heartRate = calculate(timerValue);   
     printDisplay(heartRate);
     
   }
 } 
}


uint16_t calculate(uint16_t *timerValue) {
  
  uint16_t timeSum, averageTime, heartRate = 0;
  
  for(int i=0;i<=4;i++) {
     timeSum += timerValue[i];
   }
  
  averageTime = timeSum / 5;
  heartRate = (60 * 1000) / averageTime;
  
  return heartRate;
  
}

void initUSART(void) { 
  
  UBRR0H = UBRRH_VALUE; 
  UBRR0L = UBRRL_VALUE;
  #if USE_2X
    UCSR0A |= (1 << U2X0);
  #else
    UCSR0A &= ~(1 << U2X0);
  #endif
                     
    UCSR0B = (1 << TXEN0) | (1 << RXEN0);
    UCSR0C = (1 << UCSZ01) | (1 << UCSZ00); 

}

void printDisplay(uint16_t heartRate) {
  
  printByte(heartRate);
  /*
  transmitByte(32); 
  transmitByte(40); 
  transmitByte(98); 
  transmitByte(112); 
  transmitByte(109); 
  transmitByte(41); 
  */
  transmitByte(10);
  
}


void transmitByte(uint16_t data) {
  
  loop_until_bit_is_set(UCSR0A, UDRE0);
  UDR0 = data;
  
}

void printByte(uint16_t number) { 
  
  if(number/100 != 0) { // Hundreds
    transmitByte('0'+(number/100));
  }
  else {
    transmitByte(number/100);
  }
  transmitByte('0'+ ((number/10) % 10)); // Tens 
  transmitByte('0'+ (number % 10)); // Ones
      
}

void initADC(void) {
  
  ADMUX |= (1 << REFS0); // AVcc = 5v as Vref
  ADCSRA |= ((1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0)); // Scale ADC clock frequency from 16MHz to 125kHz
  ADCSRA |= (1 << ADEN); // enable ADC operation
  ADMUX |= ((1 << MUX2) | (1 << MUX0)); // Sensor connected to ADC5/PC5 (AKA analog pin 5)

}

uint16_t readADC(void) {
 
  ADCSRA |= (1 << ADSC); //request Start Conversion
  loop_until_bit_is_clear (ADCSRA, ADSC); // wait until ADSC bit is reset
  return(ADC);
  
}

static inline void initTimer1(void) {
  
  TCCR1A = 0;
  TCCR1B = _BV(CS10) | _BV(CS12);
     
}

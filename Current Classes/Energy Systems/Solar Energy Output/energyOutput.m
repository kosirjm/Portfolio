clear all;

% Needed Variables
yearDay = 59 + 20;
longitude = 84.745;
latitude = 39.507;
LSTM = 5;
v = 54;
A = 1.6;
r = .156;
pr = .2;
solarHourFlux = [0 0];

% loop through for every hour from sunrise to sunset
for (i = 7:17)
    solarHourFlux = vertcat(solarHourFlux, solarFlux(i, yearDay, latitude, longitude, LSTM));
    
end

% remove first value which was used as a place holder
solarHourFlux(1,:) = [];

% Calculate solar panels output
outputEnergy = solarHourFlux(:,2) .* A * r * pr;

% Plot Solar flux in 
plot(7:17,solarHourFlux(:,1));
hold on;
plot(7:17,solarHourFlux(:,2));

% Plot output of solar panel each our
figure(2)
plot(7:17, outputEnergy);

% total energy output of solar panel
dayTotal = sum(outputEnergy)
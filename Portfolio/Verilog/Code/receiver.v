module receiver(
clk,
rst_n,
sent_data,
rdy_i,
data_i,
ack_i,
pulse_got
);
			
input clk, rst_n;
output [7:0] sent_data; // where the info is stored
reg [7:0] sent_data; 
input rdy_i; // tells us there's a message
output ack_i; // signal to say we've read it
reg ack_i;
input [7:0] data_i; // what they send
output pulse_got; // sends a pulse for each got packet
reg pulse_got;

reg [2:0]CS;
reg [2:0]NS;

parameter 
	RECEIVER_RESET = 3'h0,
	RECEIVER_WAIT = 3'h1, 
	RECEIVER_RECEIVE = 3'h2, 
	RECEIVER_RECEIVE_ACK = 3'h3, 
	RECEIVER_SEND_ACK = 3'h4; 

always @(posedge clk or negedge rst_n)
begin
	if (rst_n == 1'b0)
	begin
		CS <= RECEIVER_RESET;
	end
	else
	begin
		CS <= NS;
	end
end

// State transitions
reg [5:0]count;
always @(CS or rdy_i or count)
begin
	case (CS)
		RECEIVER_RESET : 
		begin
			NS = RECEIVER_WAIT;
		end
		RECEIVER_WAIT : 
		begin
			if (rdy_i == 1'b1)
				NS = RECEIVER_RECEIVE;
			else
				NS = RECEIVER_WAIT;
		end
		RECEIVER_RECEIVE : 
		begin
			NS = RECEIVER_RECEIVE_ACK;
		end
		RECEIVER_RECEIVE_ACK : 
		begin
			if (count < 6'h30)
				NS = RECEIVER_RECEIVE_ACK;
			else
				NS = RECEIVER_SEND_ACK;

		end
		RECEIVER_SEND_ACK : 
		begin
			if (rdy_i == 1'b0)
				NS = RECEIVER_WAIT;
			else
				NS = RECEIVER_SEND_ACK;
		end
		default:
		begin
			NS = RECEIVER_RESET;
		end
	endcase
end

always @(posedge clk or negedge rst_n)
begin
	if (rst_n == 1'b0)
	begin
		ack_i <= 1'b0;
		sent_data <= 7'd0; 
		pulse_got <= 1'b0;
	end
	else
	begin
		case (CS)
			RECEIVER_RESET : 
			begin
				ack_i <= 1'b0;
				pulse_got <= 1'b0;
			end
			RECEIVER_WAIT : 
			begin
				ack_i <= 1'b0;
				pulse_got <= 1'b0;
			end
			RECEIVER_RECEIVE : 
			begin
				pulse_got <= 1'b1;
				ack_i <= 1'b1;
				sent_data <= data_i; 
				count <= 6'd0;
			end
			RECEIVER_RECEIVE_ACK : 
			begin
				pulse_got <= 1'b0;
				ack_i <= 1'b1;
				count <= count + 1'b1;
				sent_data <= data_i; 
			end
			RECEIVER_SEND_ACK : 
			begin
				ack_i <= 1'b0;
			end
			default:
			begin
				ack_i <= 1'b0;
			end
		endcase
	end
end

endmodule

module sender(
clk,
rst_n,
send_data,
do_now,
rdy_o,
data_o,
ack_o,
done_pulse
);
			
input clk, rst_n;
input [7:0] send_data; // the packet in a register to send
input do_now; // has to be a single pulse
output rdy_o; // tell the receiver we're sending
reg rdy_o;
input ack_o; // signal from the receiver to say he's got
output [7:0] data_o; // what we're sending
reg [7:0]data_o;
output done_pulse;
reg done_pulse;

reg [2:0]CS;
reg [2:0]NS;

parameter 
	SENDER_RESET = 3'h0,
	SENDER_WAIT = 3'h1, 
	SENDER_SEND = 3'h2, 
	SENDER_SEND_RDY = 3'h3, 
	SENDER_RECEIVE_ACK = 3'h4, 
	SENDER_SEND_RDY_DONE = 3'h5; 

always @(posedge clk or negedge rst_n)
begin
	if (rst_n == 1'b0)
	begin
		CS <= SENDER_RESET;
	end
	else
	begin
		CS <= NS;
	end
end

// State transitions
always @(CS or ack_o or do_now)
begin
	case (CS)
		SENDER_RESET : 
		begin
			NS = SENDER_WAIT;
		end
		SENDER_WAIT : 
		begin
			if (do_now == 1'b1)
				NS = SENDER_SEND;
			else
				NS = SENDER_WAIT;
		end
		SENDER_SEND : 
		begin
			NS = SENDER_RECEIVE_ACK;
		end
		SENDER_RECEIVE_ACK :
		begin
			if (ack_o == 1'b1)
				NS = SENDER_SEND_RDY;
			else
				NS = SENDER_RECEIVE_ACK;
		end
		SENDER_SEND_RDY :
		begin
			if (ack_o == 1'b0)
				NS = SENDER_SEND_RDY_DONE;
			else
				NS = SENDER_SEND_RDY;
		end
		SENDER_SEND_RDY_DONE : 
		begin
			NS = SENDER_WAIT;
		end
		default:
		begin
			NS = SENDER_RESET;
		end
	endcase
end

always @(posedge clk or negedge rst_n)
begin
	if (rst_n == 1'b0)
	begin
		rdy_o <= 1'b0;
		done_pulse <= 1'b0;
		data_o <= 7'd0;
	end
	else
	begin
		case (CS)
			SENDER_RESET : 
			begin
				rdy_o <= 1'b0;
				done_pulse <= 1'b0;
				data_o <= 7'd0;
			end
			SENDER_WAIT : 
			begin
				rdy_o <= 1'b0;
				done_pulse <= 1'b0;
				data_o <= 7'd0;
			end
			SENDER_SEND : 
			begin
				rdy_o <= 1'b1;
				data_o <= send_data;
				done_pulse <= 1'b0;
			end
			SENDER_RECEIVE_ACK :
			begin
				rdy_o <= 1'b1;
				data_o <= send_data;
				done_pulse <= 1'b0;
			end
			SENDER_SEND_RDY : 
			begin
				rdy_o <= 1'b1;
				done_pulse <= 1'b0;
			end
			SENDER_SEND_RDY_DONE : 
			begin
				rdy_o <= 1'b0;
				done_pulse <= 1'b1;
			end
			default:
			begin
				rdy_o <= 1'b0;
				data_o <= 7'd0;
				done_pulse <= 1'b0;
			end
		endcase
	end
end

endmodule

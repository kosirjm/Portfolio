/*

This software/hardware design was originally created by Peter Jamieson
jamiespa@muohio.edu  http://www.users.muohio.edu/jamiespa/

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

*/ 
module debounce_DE2_SW (clk, rst_n, SW, SWO);
input rst_n, clk;
input [17:0]SW;
output [17:0]SWO;
wire [17:0]SWO;

debouncer sw0(clk, rst_n, SW[0], SWO[0]);
debouncer sw1(clk, rst_n, SW[1], SWO[1]);
debouncer sw2(clk, rst_n, SW[2], SWO[2]);
debouncer sw3(clk, rst_n, SW[3], SWO[3]);
debouncer sw4(clk, rst_n, SW[4], SWO[4]);
debouncer sw5(clk, rst_n, SW[5], SWO[5]);
debouncer sw6(clk, rst_n, SW[6], SWO[6]);
debouncer sw7(clk, rst_n, SW[7], SWO[7]);
debouncer sw8(clk, rst_n, SW[8], SWO[8]);
debouncer sw9(clk, rst_n, SW[9], SWO[9]);
debouncer sw10(clk, rst_n, SW[10], SWO[10]);
debouncer sw11(clk, rst_n, SW[11], SWO[11]);
debouncer sw12(clk, rst_n, SW[12], SWO[12]);
debouncer sw13(clk, rst_n, SW[13], SWO[13]);
debouncer sw14(clk, rst_n, SW[14], SWO[14]);
debouncer sw15(clk, rst_n, SW[15], SWO[15]);
debouncer sw16(clk, rst_n, SW[16], SWO[16]);
debouncer sw17(clk, rst_n, SW[17], SWO[17]);

endmodule

module debouncer (clk, rst_n, noisy, clean);
input rst_n, clk, noisy;
output clean;
   
reg xnew, clean;

reg [1:0] b_state;
reg [19:0] b_counter;

parameter 	ON=		2'd0, 
		ON_2_OFF=	2'd1, 
		OFF=		2'd2, 
		OFF_2_ON=	2'd3;

always @ (posedge clk or negedge rst_n) 
begin
	if (rst_n == 1'b0) 
	begin
		b_state <= OFF;
		b_counter <= 20'b0;
		clean <= 1'b0;
	end
	else 
	begin
		case (b_state)
			ON:
			begin
				b_state <= (noisy == 1'b0) ? ON_2_OFF : ON; 
				b_counter <= 20'b0;
				clean <= 1'b1;
			end
			OFF:
			begin
				b_state <= (noisy == 1'b1) ? OFF_2_ON : OFF; 
				b_counter <= 20'b0;
				clean <= 1'b0;
			end
			ON_2_OFF:
			begin
				b_state <= (b_counter >= 20'd5000) ? OFF : ON_2_OFF; 
				b_counter <= b_counter + 1'b1;
				clean <= 1'b1;
			end
			OFF_2_ON:
			begin
				b_state <= (b_counter >= 20'd5000) ? ON : OFF_2_ON; 
				b_counter <= b_counter + 1'b1;
				clean <= 1'b0;
			end
		endcase
	end
end
	
endmodule

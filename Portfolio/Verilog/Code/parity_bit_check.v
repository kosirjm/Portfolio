module parity_bit_check(tbyte, check);
input [7:0]tbyte;
output check;
wire check;
	
wire [3:0]total_ones;
assign total_ones = tbyte[0] + tbyte[1] + tbyte[2] + tbyte[3] + tbyte[4] + tbyte[5] + tbyte[6];
// turns on if the parity matches the top most bit
assign check = (tbyte[7] == total_ones[0]) ? 1'b1 : 1'b0;

endmodule

// Small 8 by 8 Character Generator ROM for Video Display
// Each character is eight 8-bits words of pixel data
module char_rom (character_address, font_row, font_col, rom_mux_output);
	input [5:0] character_address;
	input [2:0] font_row, font_col;
	output rom_mux_output;

	wire [8:0] rom_address;
	wire [7:0] q;
	reg rom_mux_output;

	assign rom_address = {character_address,font_row}; 

	lpm_rom	lpm_rom_component (
				.address (rom_address),
				.q (q));
	defparam
		lpm_rom_component.lpm_width = 8,
		lpm_rom_component.lpm_widthad = 9,
		lpm_rom_component.lpm_address_control = "UNREGISTERED",
		lpm_rom_component.lpm_outdata = "UNREGISTERED",
		lpm_rom_component.lpm_file = "TCGROM.MIF";


	// Mux to pick off correct rom data bit from 8-bit word
	// for on screen character generation
	always
	case (font_col)
	0: rom_mux_output = q[7];
	1: rom_mux_output = q[6];
	2: rom_mux_output = q[5];
	3: rom_mux_output = q[4];
	4: rom_mux_output = q[3];
	5: rom_mux_output = q[2];
	6: rom_mux_output = q[1];
	7: rom_mux_output = q[0];
	endcase
endmodule


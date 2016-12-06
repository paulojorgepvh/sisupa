var Upa = Upa || {};

Upa.MaskCpf = (function(){
	
	function MaskCpf(){
		this.inputCpf = $('.js-cpf');
	}
	
	MaskCpf.prototype.enable = function(){
				
		this.inputCpf.mask('000.000.000-00', {reverse: false});
		
	}
	
	return MaskCpf;
}());

$(function(){
	
	var maskCpf = new Upa.MaskCpf();
	maskCpf.enable();
});
var Upa = Upa || {};

Upa.MaskCep = (function(){
	
	function MaskCep(){
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function(){
				
		this.inputCep.mask('00000-000', {reverse: false});
		
	}
	
	return MaskCep;
}());

$(function(){
	
	var maskCep = new Upa.MaskCep();
	maskCep.enable();
});
var Upa = Upa || {};

Upa.MaskDate = (function(){
	function MaskDate(){
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function(){
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation:'bottom',
			language:'pt-BR',
			autoclose:true
		})
	}
	 return MaskDate;
	
}());

$(function() {
	var maskDate = new Upa.MaskDate();
	maskDate.enable();
})
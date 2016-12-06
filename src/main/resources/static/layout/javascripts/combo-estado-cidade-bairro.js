var UPA = UPA || {};

UPA.ComboUF = (function(){
	
	function ComboUF(){
		this.combo = $('#uf');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboUF.prototype.iniciar = function(){
		this.combo.on('change', onUfAlterado.bind(this));
	}
	
	function onUfAlterado(){
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboUF;
	
}());

UPA.ComboMunicipio = (function(){
	
	function ComboMunicipio(comboUF){
		this.comboUF = comboUF;
		this.combo = $('#municipio');
		this.imgLoading = $('.js-img-loading');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboMunicipio.prototype.iniciar = function(){
		reset.call(this);
		this.comboUF.on('alterado', onUfAlterado.bind(this));
		this.combo.on('change', onMunicipioAlterado.bind(this));
	}
	
	function onMunicipioAlterado(){
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	function onUfAlterado(evento, idEstado){
		if(idEstado){
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: {'uf': idEstado},
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarMunicipiosFinalizado.bind(this));
		}else{
			reset.call(this);
		}
	}
	
	function onBuscarMunicipiosFinalizado(municipios){
		var options = [];
		
		options.push('<option value="">Selecione o município</option>');
		
		municipios.forEach(function(municipio){
			options.push('<option value="'+ municipio.id + '">' + municipio.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	}
	
	function reset(){
		this.combo.html('<option value="">Selecione o município</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao(){
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao(){
		this.imgLoading.hide();
	}
	
	return ComboMunicipio;
	
}());

UPA.ComboBairro = (function(){
	
	function ComboBairro(comboMunicipio){
		this.comboMunicipio = comboMunicipio;
		this.combo = $('#bairro');
		this.imgLoading = $('.js-img-loading');
	}
	
	ComboBairro.prototype.iniciar = function(){
		reset.call(this);
		this.comboMunicipio.on('alterado', onMunicipioAlterado.bind(this));
	}
	
	function onMunicipioAlterado(evento, idMunicipio){
		if(idMunicipio){
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: {'municipio': idMunicipio},
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarBairrosFinalizado.bind(this));
		}else{
			reset.call(this);
		}
	}
	
	function onBuscarBairrosFinalizado(bairros){
		var options = [];
		
		options.push('<option value="">Selecione o bairro</option>');
		
		bairros.forEach(function(bairro){
			options.push('<option value="'+ bairro.id + '">' + bairro.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	}
	
	function reset(){
		this.combo.html('<option value="">Selecione o bairro</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao(){
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao(){
		this.imgLoading.hide();
	}
	
	return ComboBairro;
	
}());


$(function(){
	
	var comboUF = new UPA.ComboUF();
	comboUF.iniciar();
	
	var comboMunicipio = new UPA.ComboMunicipio(comboUF);
	comboMunicipio.iniciar();
	
	var comboBairro = new UPA.ComboBairro(comboMunicipio);
	comboBairro.iniciar();
	
	
});
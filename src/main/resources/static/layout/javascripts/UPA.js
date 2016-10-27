$(function() {
	
	/*Muda Status do Profissional de Saúde*/
	
	$('.js-atualizar-status').on('click',function(event){
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url:urlReceber,
			type:'PUT'
		});
		
		response.done(function(e){
			var id=botaoReceber.data('id');
			var classAtivo = 'glyphicon glyphicon-ok-sign text-success';
			var classInativo = 'glyphicon glyphicon-remove-sign text-danger';
			if($('[data-role='+id+']').hasClass(classAtivo)){
				$('[data-role='+id+']').removeClass(classAtivo).addClass(classInativo);
			}else{
				$('[data-role='+id+']').removeClass(classInativo).addClass(classAtivo);
			}
		});
		
		response.fail(function(e){
			console.log(e);
			alert('Erro ao tentar alterar o status!');
		});
	});
});
package br.com.portovelho.sisupas.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	
	private final int maximoLinks = 2;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		String httpUrl = httpServletRequest.getRequestURL()
				.append(httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);

	}

	public List<T> getConteudo() {
		return page.getContent();
	}

	public boolean isVazia() {
		return page.getContent().isEmpty();
	}

	public int getAtual() {
		return page.getNumber();
	}

	public boolean isPrimeira() {
		return page.isFirst();
	}

	public boolean isUltima() {
		return page.isLast();
	}

	public int getTotalPaginas() {
		return page.getTotalPages();
	}

	public boolean mostraPaginacao() {
		/*int totalPages = page.getTotalPages();
		int size = page.getSize();
		int numberOfElements = page.getNumberOfElements();
		int size2 = page.getContent().size();
		long totalElements = page.getTotalElements();*/
		
		
		return page.getTotalElements() > page.getSize() ? true : false;
	}
	
	public int getUltimaPagina(){
		return (int) (page.getTotalElements() / page.getSize());
	}

	public String urlParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

	public String urlOrdenada(String propriedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());

		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));

		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}

	public String inverterDirecao(String propriedade) {
		String direcao = "asc";

		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}

		return direcao;
	}

	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}

	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;

		if (order == null) {
			return false;
		}

		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}

	public int getMaximoLinks() {
		return maximoLinks;
	}
}

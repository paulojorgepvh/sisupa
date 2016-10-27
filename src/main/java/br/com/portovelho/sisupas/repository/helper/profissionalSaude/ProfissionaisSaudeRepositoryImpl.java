package br.com.portovelho.sisupas.repository.helper.profissionalSaude;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.portovelho.sisupas.model.ProfissionalSaude;
import br.com.portovelho.sisupas.repository.filter.ProfissionalSaudeFiltro;

public class ProfissionaisSaudeRepositoryImpl implements ProfissionaisSaudeRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<ProfissionalSaude> filtrar(ProfissionalSaudeFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ProfissionalSaude.class);

		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistroPorPagina;

		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistroPorPagina);

		Sort sort = pageable.getSort();
		
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(ProfissionalSaudeFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNomeCompleto())) {
				criteria.add(Restrictions.ilike("nomeCompleto", filtro.getNomeCompleto(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getLogin())) {
				criteria.add(Restrictions.ilike("login", filtro.getLogin(), MatchMode.ANYWHERE));
			}
		}
	}

	private Long total(ProfissionalSaudeFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ProfissionalSaude.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
package br.com.portovelho.sisupas.repository.helper.bairro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.portovelho.sisupas.model.Bairro;
import br.com.portovelho.sisupas.repository.filter.BairroFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class BairrosRepositoryImpl implements BairrosRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Bairro> filtrar(BairroFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bairro.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(BairroFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getMunicipio())) {
				criteria.add(Restrictions.eq("municipio", filtro.getMunicipio()));
			}
		}
	}

	private Long total(BairroFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bairro.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}

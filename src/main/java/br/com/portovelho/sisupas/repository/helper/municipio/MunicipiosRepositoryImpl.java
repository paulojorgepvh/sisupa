package br.com.portovelho.sisupas.repository.helper.municipio;

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

import br.com.portovelho.sisupas.model.Municipio;
import br.com.portovelho.sisupas.repository.filter.MunicipioFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class MunicipiosRepositoryImpl implements MunicipiosRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Municipio> filtrar(MunicipioFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Municipio.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(MunicipioFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}

	private Long total(MunicipioFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Municipio.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}

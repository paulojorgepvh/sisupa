package br.com.portovelho.sisupas.repository.helper.procedimentoInterno;

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

import br.com.portovelho.sisupas.model.ProcedimentoInterno;
import br.com.portovelho.sisupas.repository.filter.ProcedimentoInternoFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class ProcedimentosInternosRepositoryImpl implements ProcedimentosInternosRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<ProcedimentoInterno> filtrar(ProcedimentoInternoFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ProcedimentoInterno.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(ProcedimentoInternoFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			if (filtro.getTipoSalaAtendimento() != null) {
				if (filtro.getTipoSalaAtendimento().getDescricao() != null) {
					criteria.add(Restrictions.eq("tipoSalaAtendimento", filtro.getTipoSalaAtendimento()));
				}
			}

		}
	}

	private Long total(ProcedimentoInternoFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ProcedimentoInterno.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}

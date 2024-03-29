package br.com.portovelho.sisupas.repository.helper.salaAtendimento;

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

import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.filter.SalaAtendimentoFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class SalasAtendimentoRepositoryImpl implements SalasAtendimentoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<SalaAtendimento> filtrar(SalaAtendimentoFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(SalaAtendimento.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(SalaAtendimentoFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			if (filtro.getTipoSalaAtendimento() != null) {
				if (filtro.getTipoSalaAtendimento().getDescricao() != null) {
					criteria.add(Restrictions.eq("tipoSalaAtendimento", filtro.getTipoSalaAtendimento()));
				}
			}
			if (filtro.getStatusSalaAtendimento() != null) {
				if (filtro.getStatusSalaAtendimento().getDescricao() != null) {
					criteria.add(Restrictions.eq("statusSalaAtendimento", filtro.getStatusSalaAtendimento()));
				}
			}
		}
	}

	private Long total(SalaAtendimentoFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(SalaAtendimento.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}

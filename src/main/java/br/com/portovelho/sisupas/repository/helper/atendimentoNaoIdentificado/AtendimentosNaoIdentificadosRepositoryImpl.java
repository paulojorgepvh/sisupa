package br.com.portovelho.sisupas.repository.helper.atendimentoNaoIdentificado;

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

import br.com.portovelho.sisupas.model.AtendimentoNaoIdentificado;
import br.com.portovelho.sisupas.repository.filter.AtendimentoNaoIdentificadoFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class AtendimentosNaoIdentificadosRepositoryImpl implements AtendimentosNaoIdentificadosRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<AtendimentoNaoIdentificado> filtrar(AtendimentoNaoIdentificadoFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(AtendimentoNaoIdentificado.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(AtendimentoNaoIdentificadoFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			
			/*if (filtro.getTipoSalaAtendimento() != null) {
				if (filtro.getTipoSalaAtendimento().getDescricao() != null) {
					criteria.add(Restrictions.ilike("tipoSalaAtendimento", filtro.getTipoSalaAtendimento().name()));
				}
			}*/

		}
	}

	private Long total(AtendimentoNaoIdentificadoFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(AtendimentoNaoIdentificado.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}


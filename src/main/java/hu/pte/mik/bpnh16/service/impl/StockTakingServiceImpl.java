package hu.pte.mik.bpnh16.service.impl;

import hu.pte.mik.bpnh16.service.StockTakingService;
import hu.pte.mik.bpnh16.domain.StockTaking;
import hu.pte.mik.bpnh16.repository.StockTakingRepository;
import hu.pte.mik.bpnh16.repository.search.StockTakingSearchRepository;
import hu.pte.mik.bpnh16.service.dto.StockTakingDTO;
import hu.pte.mik.bpnh16.service.mapper.StockTakingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StockTaking.
 */
@Service
@Transactional
public class StockTakingServiceImpl implements StockTakingService {

    private final Logger log = LoggerFactory.getLogger(StockTakingServiceImpl.class);

    private final StockTakingRepository stockTakingRepository;

    private final StockTakingMapper stockTakingMapper;

    private final StockTakingSearchRepository stockTakingSearchRepository;

    public StockTakingServiceImpl(StockTakingRepository stockTakingRepository, StockTakingMapper stockTakingMapper, StockTakingSearchRepository stockTakingSearchRepository) {
        this.stockTakingRepository = stockTakingRepository;
        this.stockTakingMapper = stockTakingMapper;
        this.stockTakingSearchRepository = stockTakingSearchRepository;
    }

    /**
     * Save a stockTaking.
     *
     * @param stockTakingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockTakingDTO save(StockTakingDTO stockTakingDTO) {
        log.debug("Request to save StockTaking : {}", stockTakingDTO);
        StockTaking stockTaking = stockTakingMapper.toEntity(stockTakingDTO);
        stockTaking = stockTakingRepository.save(stockTaking);
        StockTakingDTO result = stockTakingMapper.toDto(stockTaking);
        stockTakingSearchRepository.save(stockTaking);
        return result;
    }

    /**
     * Get all the stockTakings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockTakingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockTakings");
        return stockTakingRepository.findAll(pageable)
            .map(stockTakingMapper::toDto);
    }

    /**
     * Get one stockTaking by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StockTakingDTO findOne(Long id) {
        log.debug("Request to get StockTaking : {}", id);
        StockTaking stockTaking = stockTakingRepository.findOne(id);
        return stockTakingMapper.toDto(stockTaking);
    }

    /**
     * Delete the stockTaking by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockTaking : {}", id);
        stockTakingRepository.delete(id);
        stockTakingSearchRepository.delete(id);
    }

    /**
     * Search for the stockTaking corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockTakingDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of StockTakings for query {}", query);
        Page<StockTaking> result = stockTakingSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(stockTakingMapper::toDto);
    }
}

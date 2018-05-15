package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Contract;
import com.atibusinessgroup.fmp.domain.InternetFares;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Spring Data MongoDB repository for the InternetFares entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InternetFaresRepository extends MongoRepository<InternetFares, String> {

	Contract findOneById(String idContract);

	Page<InternetFares> findAllByFlightOriginAllIgnoreCase(String origin , Pageable pageable);
	
	Page<InternetFares> findAllByFlightOriginAndFlightDestinationAllIgnoreCase(String origin, String destination,
			Pageable pageable);

	Page<InternetFares> findAllByCarrierCodeAllIgnoreCase(String carrier, Pageable pageable);

	Page<InternetFares> findAllByFlightDestinationAllIgnoreCase(String destination, Pageable pageable);

	Page<InternetFares> findAllBySiteNameAllIgnoreCase(String website, Pageable pageable);

	Page<InternetFares> findAllByFlightOriginAndCarrierCodeAllIgnoreCase(String origin, String carrier, Pageable pageable);

	Page<InternetFares> findAllByCarrierCodeAndSiteNameAllIgnoreCase(String carrier, String website, Pageable pageable);

	Page<InternetFares> findAllByFlightOriginAndCarrierCodeAndSiteNameAllIgnoreCase(String origin, String carrier,
			String website, Pageable pageable);

	Page<InternetFares> findAllByFlightOriginAndFlightDestinationAndSiteNameAllIgnoreCase(String origin,
			String destination, String website, Pageable pageable);

	Page<InternetFares> findAllByFlightOriginAndFlightDestinationAndCarrierCodeAllIgnoreCase(String origin,
			String destination, String carrier, Pageable pageable);

	Page<InternetFares> findAllByFlightOriginAndSiteNameAllIgnoreCase(String origin, String website, Pageable pageable);

	Page<InternetFares> findAllByFlightOriginAndFlightDestinationAndCarrierCodeAndSiteNameAllIgnoreCase(String origin,
			String destination, String carrier, String website, Pageable pageable);

	Page<InternetFares> findAllByFlightDestinationAndCarrierCodeAndSiteNameAllIgnoreCase(String destination,
			String carrier, String website, Pageable pageable);

	Page<InternetFares> findAllByFlightDestinationAndSiteNameAllIgnoreCase(String destination, String website,
			Pageable pageable);

	Page<InternetFares> findAllByFlightDestinationAndCarrierCodeAllIgnoreCase(String destination, String carrier,
			Pageable pageable);

}

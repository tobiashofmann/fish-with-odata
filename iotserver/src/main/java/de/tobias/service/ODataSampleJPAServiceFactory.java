/**
 * 
 */
package de.tobias.service;

import javax.persistence.Persistence;

import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;

/**
 * OData magic
 * @author Tobias Hofmann
 *
 */
public class ODataSampleJPAServiceFactory extends ODataJPAServiceFactory {

	// persistence unit, as given in persistence.xml
	private static final String PUNIT_NAME = "iotdemo";
	// page size used by olingo
	private static final int PAGE_SIZE = 50;

	/**
	 * 
	 */
	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {

		ODataJPAContext oDataJPAContext = getODataJPAContext();
		oDataJPAContext.setEntityManagerFactory(Persistence.createEntityManagerFactory(PUNIT_NAME));
		oDataJPAContext.setPersistenceUnitName(PUNIT_NAME);
		oDataJPAContext.setPageSize(PAGE_SIZE);
		setDetailErrors(true);
		return oDataJPAContext;
	}
}

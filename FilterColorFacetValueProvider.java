package de.hybris.merchandise.search.providers;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductReviewAverageRatingValueProvider;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;


public class FilterColorFacetValueProvider extends ProductReviewAverageRatingValueProvider
{
	private static final Logger LOG = Logger.getLogger(FilterColorFacetValueProvider.class);

	@Override
	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final LanguageModel language, final Object value)
	{
		List<String> rangeNameList = null;
		try
		{
			rangeNameList = getRangeNameList(indexedProperty, value);
		}
		catch (final FieldValueProviderException e)
		{
			LOG.error("Could not get Range value", e);
		}
		String rangeName = null;
		if (CollectionUtils.isNotEmpty(rangeNameList))
		{
			rangeName = rangeNameList.get(0);
		}
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
				language == null ? null : language.getIsocode());
		final Object valueToPass = (rangeName == null ? value : rangeName);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, valueToPass));
		}
	}

}





//	Logger LOG = Logger.getLogger(FilterColorFacetValueProvider.class);
//
//	@SuppressWarnings("deprecation")
//
//	@Override
//	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
//			final Object model) throws FieldValueProviderException
//	{ // YTODO Auto-generated method stub
//
//		if (model instanceof ProductModel)
//		{
//			final MerchandiseProductModel productcolor = (MerchandiseProductModel) model;
//
//			LOG.info("Color=====" + productcolor);
//
//
//			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
//
//			if (productcolor.getColor() != null)
//			{
//				final String color = productcolor.getColor().getCode();
//				LOG.info("Color+++" + color);
//				fieldValues.add(new FieldValue(indexedProperty.getName(), color));
//			}
//
//			LOG.info("Field Values=====" + fieldValues);
//			return fieldValues;
//		}
//		else
//		{
//			throw new FieldValueProviderException("Field Value Exception ++++++++++");
//		}
//
//	}
//}



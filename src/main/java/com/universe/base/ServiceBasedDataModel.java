/*
 * Copyright (c) 2015 Univeris Corporation. All Rights Reserved.
 *
 * This computer program constitutes confidential and proprietary information of Univeris Corporation
 * and is protected by copyright and other intellectual property laws.  Unless you have entered into a
 * written agreement with Univeris Corporation granting you rights to use this computer program in
 * source code form, you have no rights, and are not authorized, to possess, view, copy, distribute or
 * use this computer program in the form attached in any manner whatsoever and must promptly return this
 * program and all copies thereof in your possession or control to Univeris Corporation.
 * Unauthorized possession, viewing, copying, distribution or use of this computer program or any portion
 * thereof may result in liability and will be prosecuted to the maximum extent possible under the law.
 *
 * File Id: $Id$
 * Current Revision: $Rev$
 * Last Modified: $LastChangedDate$
 * Last Modified By: $LastChangedBy$.
 */

package com.universe.base;

import com.univeris.ewm.dataobjects.support.BaseDto;
import com.univeris.ewm.provider.data.basedataaccess.PaginatedResults;
import com.univeris.ewm.service.data.common.baseservice.ServiceCommons;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.SerializableDataModel;
import org.richfaces.model.FilterField;
import org.richfaces.model.Modifiable;
import org.richfaces.model.Ordering;
import org.richfaces.model.SortField2;

import javax.el.Expression;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic Pagination Data Model that fetches from database only the necessary data displayed per page
 *
 * @author Nikolai Gagov
 *         <p/>
 *         A generic class for Sorting and Paginating in the database with Richfaces.
 *         It is good for large datasets as the original RichFaces data model would require the whole data set
 *         to be cached in memory.
 *         Entity (or Dao) specific methods are delegated to the PaginationDataProvider<T, U> interface.
 *         This code is based on materials published at Electric Programmer http://eclecticprogrammer.com/ on July 30th, 2008:
 *         http://eclecticprogrammer.com/2008/07/30/a-generic-superclass-for-sorting-and-paginating-in-the-database-with-richfaces/
 *         <p/>
 *         Note that the instance of this data model has to be in the Session or Conversation scope.
 * @param <T> Item object type
 * @param <U> Item's key object type  (Id)
 */
//@BusinessExceptionInterceptor
//@ClassVersionDetails(revision = "$Rev$", id = "$Id$")
public class ServiceBasedDataModel <T extends BaseDto, U> extends SerializableDataModel implements Modifiable {
    protected U _currentPk;
    protected boolean _descending = true;
    protected String _sortField;
    private boolean _detached = false;
    protected List<U> _wrappedKeys = new ArrayList<U>();
    protected Integer _rowCount;
    protected Map<U, T> _wrappedData = new HashMap<U, T>();
    protected int _rowIndex;
    private ServiceCommons<T, U> _dataProvider;
    private T _searchInstance;

    PaginatedResults<T> _results;


    public ServiceBasedDataModel(ServiceCommons<T, U> dataProvider, T searchInstance) {
        _dataProvider = dataProvider;
        _searchInstance = searchInstance;
        //_results = _dataProvider.findObjects(0, numberOfRows, _sortField, _descending, _searchInstance);
        _rowCount = dataProvider.getAll().size();
    }

    /**
     * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
     *      These method should just get an instance variable that is Serializable,
     *      and which you can use to look up an individual object that will populate a row in your table.
     */
    @Override
    public Object getRowKey() {
        return _currentPk;
    }

    /**
     * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(Object)
     *      These method sets an instance variable that is Serializable,
     *      and which you can use to look up an individual object that will populate a row in your table.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setRowKey(final Object key) {
        this._currentPk = (U)key;
    }

    /**
     * @see org.ajax4jsf.model.SerializableDataModel#update()
     *      This method should be overridden to allow saving changes.
     */
    @Override
    public void update() {
//        if(getSortFieldObject() != null) {
//            final String newSortField = getSortFieldObject().toString();
//            if(newSortField.equals(_sortField)) {
//                _descending = !_descending;
//            }
//            _sortField = newSortField;
//        }
        this._detached = false;
    }

    /**
     * Get sorting field name from request.
     * @return Object
     */
//    protected Object getSortFieldObject() {
//        final FacesContext context = FacesContext.getCurrentInstance();
//        return context.getExternalContext().getRequestParameterMap().get("_sortField");
//    }

    /**
     * @param sortField - field name the query will be ordered by
     */
    public void setSortField(final String sortField) {
        if(this._sortField.equals(sortField)) {
            _descending = !_descending;
        } else {
            this._sortField = sortField;
        }
    }

    /**
     * @return sorting field's name
     */
    public String getSortField() {
        return _sortField;
    }

    /**
     * @see org.ajax4jsf.model.ExtendedDataModel#getSerializableModel(org.ajax4jsf.model.Range)
     */
    @Override
    public SerializableDataModel getSerializableModel(final Range range) {
        if(_wrappedKeys != null) {
            _detached = true;
            return this;
        }
        return null;
    }

    /**
     * @see javax.faces.model.DataModel#setRowIndex(int)
     *      Set row index mapped from UISelection.
     *      This is essential for @DataModeSelection to work properly.
     */
    @Override
    public void setRowIndex(final int rowIndex) {
        Object originalKey = null;
        if(rowIndex >= 0 && rowIndex < _wrappedKeys.size()) {
            originalKey = _wrappedKeys.get(rowIndex);
        }
        this.setRowKey(originalKey);
        _rowIndex = rowIndex;
    }

    /**
     * @see javax.faces.model.DataModel#getRowIndex()
     * @return index of the surrogate key in the wrapped keys
     *      This is essential for @DataModeSelection to work properly.
     */
    @Override
    public int getRowIndex() {
        return _wrappedKeys.indexOf(this.getRowKey());
    }

    /**
     * Get data which can be wrapped.
     *
     * @return casted to DataModel object
     */
    @Override
    public Object getWrappedData() {
        return this;
    }

    /**
     * Just a rudiment for now.
     *
     * @param obj - current data
     */
    @Override
    public void setWrappedData(Object obj) {
    }

    /**
     * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext,
     *      org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range,
     *      Object)
     *      This method will be called by Richfaces, and will give you an opportunity to pull
     *      from the database just those records that will be shown to the user.
     *      However, it is important to note that this method can potentially be called many times
     *      during one request, and we only want to make one call to the database.
     *      So, we want to cache the results, but we also dont want to cache them too aggressively,
     *      for if a person requests a different page of results, or changes the sort order,
     *      then we want to fetch new results.  So, two other methods that you must override,
     *      public void update() and public SerializableDataModel getSerializableModel(Range range)
     *      come into play as well.
     *      The first check should guarantee that the model will not be updated when the jsf component
     *      is rebuilt on postback, but rather when the new model is being built.
     *      The other check, for getSortFieldObject, assures that when it is not the table itself
     *      which has an action taken on it, but rather something outside the table (such as a DataScroller),
     *      the update method will not be called.
     */
    @Override
    public void walk(final FacesContext context, final DataVisitor visitor, final Range range, final Object argument)
            throws IOException {
        final int firstRow = ((SequenceRange)range).getFirstRow();
        final int numberOfRows = ((SequenceRange)range).getRows();
//        if(_detached && getSortFieldObject() != null) {
        if(_detached) {
            for(final U key : _wrappedKeys) {
                setRowKey(key);
                visitor.process(context, key, argument);
            }
        } else { // if not serialized, than we request data from data provider
            _wrappedKeys = new ArrayList<U>();
            //Event.addDebugEvent("Fetch {0} rows starting from {1} sorted by {2} descending = {3}", numberOfRows, firstRow, _sortField == null ? "" : _sortField, _descending);

            _results = _dataProvider.findObjects(firstRow, numberOfRows, _sortField, _descending, _searchInstance);
            _rowCount = _results.getCount();

            for(final T object : _results.getRows()) {
                _wrappedKeys.add(getId(object));
                _wrappedData.put(getId(object), object);
                visitor.process(context, getId(object), argument);
            }
        }
    }

    /**
     * @see javax.faces.model.DataModel#isRowAvailable()
     */
    @Override
    public boolean isRowAvailable() {
        if(_currentPk == null) {
            return false;
        }
        if(_wrappedKeys.contains(_currentPk)) {
            return true;
        }
        //TODO uncomment the following block whenever the FunctionalSecurity exception is resolved in AbstractBaseService.
        /*if(_dataProvider.findById(_currentPk) != null) {
            return true;
        }*/
        return false;
    }

    /**
     * @see javax.faces.model.DataModel#getRowData()
     */
    @Override
    public Object getRowData() {
        if(_currentPk == null) {
            return null;
        }

        T object = _wrappedData.get(_currentPk);
        //TODO uncomment the following block whenever the FunctionalSecurity exception is resolved in AbstractBaseService.
        /*if(object == null) {
            object = _dataProvider.findById(_currentPk);
            _wrappedData.put(_currentPk, object);
        }*/
        return object;
    }

    /**
     * @see javax.faces.model.DataModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        if(_rowCount == null  ) {
            if (_results != null)
            _rowCount = _results.getCount();
            else
              return 0;
        }
        return _rowCount.intValue();
    }

    /**
     * @see  org.richfaces.model.Modifiable#modify(java.util.List, java.util.List)
     * @param filterFields - list of filter fields (not implemented)
     * @param sortFields - list of sorting fields (<rich:column sortBy="#{_product.name}" ../>)
     */
    public void modify(List<FilterField> filterFields, List<SortField2> sortFields) {
        this._detached = false; // Allow data fetch

        if(sortFields != null && !sortFields.isEmpty()) {
            SortField2 sortField2 = sortFields.get(0);
            Expression expression = sortField2.getExpression();
            String expressionStr = expression.getExpressionString();
            if(!expression.isLiteralText()) {
                expressionStr = expressionStr.replaceAll("[#|$]\\{.*?\\.", "")
                        .replaceAll("\\}", "");
            }
            this._sortField = expressionStr;

            Ordering ordering = sortField2.getOrdering();
            this._descending = ordering == Ordering.DESCENDING;
        }
    }

    /**
     * @param object entry object for which we create a surrogate key
     * @return U - surogate key
     */
    public U getId(T object){
        return (U)object.getPK();
    }
}
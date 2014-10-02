<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="addTechnologyModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog"
     aria-labelledby="addTechnologyModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="addTechnologyModalLabel" class="displayInLine">
            <spring:message code="create"/>&nbsp;<spring:message code="technology"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="newTechnologyForm" novalidate >
            <div class="pull-left align-right">
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="technology.technology"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               autofocus
                               ng-model="technology.technology"
                               name="technology"
                               placeholder="<spring:message code='sample.topic'/>"/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newTechnologyForm.technology.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="technology.version"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="technology.version"
                               name="version"
                               placeholder="<spring:message code='sample.version'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newTechnologyForm.version.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="technology.description"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="technology.description"
                               name="technology.description"
                               placeholder="<spring:message code='sample.description'/>"/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newTechnologyForm.description.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label><spring:message code="technology.coloursID"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="number"
                               ng-model="technology.coloursID"
                               name="coloursID"
                               placeholder="<spring:message code='sample.colour'/>"/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newTechnologyForm.coloursID.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                
                <input type="submit"
                       class="btn btn-inverse"
                       ng-click="createTechnology(newTechnologyForm);"
                       value='<spring:message code="create"/>'/>
                <button class="btn btn-inverse"
                        data-dismiss="modal"
                        ng-click="exit('#addTechnologyModal');"
                        aria-hidden="true">
                    <spring:message code="cancel"/>
                </button>
            </div>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>

<div id="updateTechnologyModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog"
     aria-labelledby="updateTechnologyModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="updateTechnologyModalLabel" class="displayInLine">
            <spring:message code="update"/>&nbsp;<spring:message code="technology"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="updateTechnologyForm" novalidate>
            <input type="hidden"
                   required
                   ng-model="technology.id"
                   name="id"
                   value="{{technology.id}}"/>

            <div class="pull-left align-right">
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="technology.technology"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               autofocus
                               required
                               ng-model="technology.technology"
                               name="technology"
                               placeholder="<spring:message code='sample.topic'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && updateTechnologyForm.name.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="technology.version"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="technology.version"
                               name="version"
                               placeholder="<spring:message code='sample.version'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && updateTechnologyForm.surname.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="technology.description"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="technology.description"
                               name="description"
                               placeholder="<spring:message code='sample.description'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && updateTechnologyForm.description.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label><spring:message code="technology.coloursID"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="number"
                               ng-model="technology.coloursID"
                               name="coloursID"
                               placeholder="<spring:message code='sample.colours'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && updateTechnologyForm.coloursID.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                
                <input type="submit"
                       class="btn btn-inverse"
                       ng-click="updateTechnology(updateTechnologyForm);"
                       value='<spring:message code="update"/>'/>
                <button class="btn btn-inverse"
                        data-dismiss="modal"
                        ng-click="exit('#updateTechnologyModal');"
                        aria-hidden="true">
                    <spring:message code="cancel"/></button>
            </div>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>

<div id="deleteTechnologyModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-labelledby="searchTechnologyModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="deleteTechnologyModalLabel" class="displayInLine">
            <spring:message code="delete"/>&nbsp;<spring:message code="technology"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="deleteTechnologyForm" novalidate>
            <p><spring:message code="delete.confirm"/>:&nbsp;{{technology.technology}}?</p>

            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="deleteTechnology();"
                   value='<spring:message code="delete"/>'/>
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#deleteTechnologyModal');"
                    aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorIllegalAccess">
        <spring:message code="request.illegal.access"/>
    </span>
</div>

<div id="searchTechnologyModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-labelledby="searchTechnologyModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="searchTechnologyModalLabel" class="displayInLine">
            <spring:message code="search"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="searchTechnologyForm" novalidate>
            <label><spring:message code="search.for"/></label>

            <div>
                <div class="input-append">
                    <input type="text"
                           autofocus
                           required
                           ng-model="searchFor"
                           name="searchFor"
                           placeholder="<spring:message code='technology'/>&nbsp;<spring:message code='technology.technology'/> "/>
                </div>
                <div class="input-append displayInLine">
                    <label class="displayInLine">
                        <span class="alert alert-error"
                              ng-show="displayValidationError && searchTechnologyForm.searchFor.$error.required">
                            <spring:message code="required"/>
                        </span>
                    </label>
                </div>
            </div>
            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="searchTechnology(searchTechnologyForm, false);"
                   value='<spring:message code="search"/>'
                    />
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#searchTechnologyModal');"
                    aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>

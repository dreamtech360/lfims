Ext.define('lfims.view.caseMaster.CaseDetailsList', {
	extend: 'Ext.panel.Panel',
	id: 'CaseDetailsList',
	alias : 'widget.CaseDetailsList',
	layout : {
		type : 'vbox',
		align : 'stretch'

	},
	items: [

	        {xtype:'CaseDetailsListView',
					    mode:'new'
			},
	        {
	        	xtype: 'splitter',
	        	collapsible: true,
	        	collapseTarget: 'prev'
	        },
	        {xtype:'CaseDetailsListView',
			    mode:'new'
	        }
	        ]
});



Ext.define('lfims.view.caseMaster.CaseDetailsListView' ,{
	extend: 'Ext.grid.Panel',
	frame: true,
	plugins: [gridheaderfilters],
	//features: [groupingFeature],
	alias : 'widget.CaseDetailsListView',
	store: 'caseMasterDetails',
	stateful: false,
	multiSelect: true,
	tbar: [
/*{
	iconCls: 'icon-add',
	flex:1,
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewCase'
}, '-',*/
{
	text:'Delete',
	flex:1,
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteCase'
}, '-',
{xtype:'combobox',
	flex: 6,
    typeAhead: true,
    fieldLabel:'Respondent Name',
    loadingText : 'Querying....',
    pageSize : 2,
    forceSelection : true,
    minChars : 1,
    triggerAction: 'all',
    selectOnTab: true,
    store: Ext.create('Ext.data.Store',  { 
        autoLoad: true,
        fields: [ 'name',
                  'value'],
        proxy: {
    		type: 'rest',
    		url: 'http://localhost:8080/lfims/webresources/picklist/cases/respondentNameList',
    		noCache: false,
    		reader: {
    			type: 'json',
    			root: 'respondentNameList',
    			totalProperty: 'results',
    			successProperty: 'success'
    		},
    		writer:{
    			type: 'json',
    			writeAllFields:true

    		},
    		listeners: {
    			exception: function(store, response, op) {
    				Ext.example.msg('Exception','Exception has occured operation could not be filfilled');
    			}
    		}

    	}/*,
        listeners: {
            load: function(store, options) {
                  var combo = Ext.getCmp('USERTYPECmbo');
                  combo.setValue(combo.getValue()); //Set the remote combo after the store loads.
        }
    }          */                 
}),

    displayField:'name',
    valueField:'id',
    listeners:{
		  scope: this,
		  'render': function(cmb, recs, idx) {
			  cmb.getStore().pageSize='2';
			  cmb.getStore().currentPage=1;
			  cmb.getStore().clearFilter();
		  }
	  }
}, '-',

{xtype:'combobox',
    typeAhead: true,
    loadingText : 'Querying....',
    flex: 6,
    pageSize : 2,
    forceSelection : true,
    minChars : 1,
    fieldLabel:'Case No',
    triggerAction: 'all',
    selectOnTab: true,
    store: Ext.create('Ext.data.Store',  { 
        autoLoad: true,
        fields: [ 'name',
                  'value'],
        proxy: {
    		type: 'rest',
    		url: 'http://localhost:8080/lfims/webresources/picklist/cases/caseNoList',
    		noCache: false,
    		reader: {
    			type: 'json',
    			root: 'caseNoList',
    			totalProperty: 'results',
    			successProperty: 'success'
    		},
    		writer:{
    			type: 'json',
    			writeAllFields:true

    		},
    		listeners: {
    			exception: function(store, response, op) {
    				Ext.example.msg('Exception','Exception has occured operation could not be filfilled');
    			}
    		}

    	}/*,
        listeners: {
            load: function(store, options) {
                  var combo = Ext.getCmp('USERTYPECmbo');
                  combo.setValue(combo.getValue()); //Set the remote combo after the store loads.
        }
    }          */                 
}),
    displayField:'name',
    valueField:'id',
    listeners:{
		  scope: this,
		  'render': function(cmb, recs, idx) {
			  cmb.getStore().pageSize='2';
			  cmb.getStore().currentPage=1;
			  cmb.getStore().clearFilter();
		  }
	  }
}, '-',

{xtype:'combobox',
    typeAhead: true,
    fieldLabel:'RP/MA number',
    flex: 6,
    loadingText : 'Querying....',
    pageSize : 2,
    forceSelection : true,
    minChars : 1,
    triggerAction: 'all',
    selectOnTab: true,
    store: Ext.create('Ext.data.Store',  { 
        autoLoad: true,
        fields: [ 'name',
                  'value'],
        proxy: {
    		type: 'rest',
    		url: 'http://localhost:8080/lfims/webresources/picklist/cases/rpmNoList',
    		noCache: false,
    		reader: {
    			type: 'json',
    			root: 'rpmNoList',
    			totalProperty: 'results',
    			successProperty: 'success'
    		},
    		writer:{
    			type: 'json',
    			writeAllFields:true

    		},
    		listeners: {
    			exception: function(store, response, op) {
    				Ext.example.msg('Exception','Exception has occured operation could not be filfilled');
    			}
    		}

    	}/*,
        listeners: {
            load: function(store, options) {
                  var combo = Ext.getCmp('USERTYPECmbo');
                  combo.setValue(combo.getValue()); //Set the remote combo after the store loads.
        }
    }          */                 
}),
    displayField:'name',
    valueField:'id',
    listeners:{
		  scope: this,
		  'render': function(cmb, recs, idx) {
			  cmb.getStore().pageSize='2';
			  cmb.getStore().currentPage=1;
			  cmb.getStore().clearFilter();
		  }
	  }
}
],
init: function() {
	console.log('Initialized Case Master List! This happens before the Application launch function is called');
},
initComponent: function() {
	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
/*{
    header: 'Respondent Name',
    dataIndex: 'respondentName',
    hidden: true,
    editor:{xtype:'combobox',
        typeAhead: true,
        triggerAction: 'all',
        selectOnTab: true,
        store: 'bankMaster',
        displayField:'name',
        valueField:'id'
    },
    filter: {xtype:"combobox", 
    	typeAhead: true,
        triggerAction: 'all',
        selectOnTab: true,
        store: 'bankMaster',
        filterName:"bankName",
        displayField:'name',
        valueField:'id', */
       /* listeners: {
        	'focus': {
        		fn: function(obj,sample){ 
        			console.log('click el');
        			Ext.each(this.getStore().getRange(),function(model){
        				console.log(model.get('id'));});
        				
        		},
        		scope:this
        		
        	}
        }*/
     /*  }
},*/
	                {header: 'Respondent Name',  dataIndex: 'respondentName',  flex: 4,field: {xtype: 'textfield'}
	                //,filter: {xtype:"textfield", filterName:"respondentName"}
	                },
	                {header: 'Case No', dataIndex: 'caseNo', flex: 4,field: {xtype: 'textfield'}
	                //,filter: {xtype:"textfield", filterName:"caseNo"}
	                },
	                {header: 'RP/MA Number',  dataIndex: 'rpmaNo',  flex: 4,field: {xtype: 'textfield'}
	                //,filter: {xtype:"textfield",filterName:"rpmaNo"}
	                },
	               
	             
	                {
	                    xtype:'actioncolumn',
	                    header : 'Actions',
	                    
	                    flex: 1,
	                   
	                    items: [{
	                    	
	                        icon: '/lfims/extjs/resources/images/diary.png',  // Use a URL in the icon config
	                        tooltip: 'Edit',
	                        iconCls: 'icon-cursor'
	                    },
	                    {
	                    	
	                        icon: '/lfims/extjs/resources/images/spacer.png',  // Use a URL in the icon config
	                       
	                    },
	                    {
	                    	
	                        icon: '/lfims/extjs/resources/images/add.png',
	                        tooltip: 'Delete',
	                        iconCls: 'icon-cursor'
	                        
	                    }]
	                }
	               
	               
	                ];

	this.callParent(arguments);

	this.viewconfig = {
			stripeRows: true,
			enableTextSelection: true
	};

	this.store.load({
		params: {
			// specify params for the first page load if using paging
			start: 0,
			limit: itemsPerPage

		}
	});
	console.log('Initialized Case Master List! This happens before the Application launch function is called');
}
});
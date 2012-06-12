Ext.define('lfims.view.caseMaster.CaseDetails', {
    extend: 'Ext.window.Window',
    alias : 'widget.CaseDetails',
    layout: 'fit',
    width: 1100,
    height:450,
    autoShow: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                items: [
					{xtype:'CaseMasterDetails',
					    mode:'new'
					}
                ]
            }
        ];
        this.callParent(arguments);
    }
});


Ext.define('lfims.view.caseMaster.CaseMasterDetails', {
	extend: 'Ext.form.Panel',
	bodyStyle: 'padding:15px 15px;',
	 alias : 'widget.CaseMasterDetails',
	 config: {
		 mode: 'edit'
		 },
	 title : 'Case Details',
	fieldDefaults: {
		labelAlign: 'top',
		msgTarget: 'side',
		width: 300

	},
	defaults: {
		border: false,
		xtype: 'panel',
		//labelStyle: 'padding-left:10px;',
		layout: 'anchor',
		flex:1

	},
	layout: {
		type: 'hbox'

	},
	autoShow: true,

	initComponent: function() {


		this.items = [
		              {
		            	  items :[ 
		            	          {
		            	        	  xtype: 'textfield',
		            	        	  name : 'respondentName',
		            	        	  fieldLabel: 'Respondent Name'
		            	          },

		            	          {
		            	        	  xtype:'combobox',
		            	        	  typeAhead: true,
		            	        	  triggerAction: 'all',
		            	        	  selectOnTab: true,
		            	        	  store: 'bankMaster',
		            	        	  displayField:'name',
		            	        	  name:'bankName',
		            	        	  valueField:'id',
		            	        	  fieldLabel: 'Applicant Name',
		            	        	  listeners:{
		            	        		  scope: this,
		            	        		  'render': function(cmb, recs, idx) {
		            	        			  cmb.getStore().pageSize='0';
		            	        			  cmb.getStore().currentPage=1;
		            	        			  cmb.getStore().clearFilter();
	  	            	        		  }
		            	        	  }
		            	          },
		            	          {
		            	        	  xtype: 'datefield',
		            	        	  fieldLabel: 'Limit Date, if Any',
		            	        	  name: 'limitDate',
		            	        	  value: new Date(),
		            	        	  format: 'Y-m-d'
		            	          },
		            	          {
		            	        	  xtype: 'datefield',

		            	        	  fieldLabel: 'Date of Filing',
		            	        	  name: 'filingDate',
		            	        	  value: new Date(),
		            	        	  format: 'Y-m-d'
		            	          },
		            	          {
		            	        	  xtype: 'datefield',

		            	        	  fieldLabel: 'Date of Closing',
		            	        	  name: 'closingDate',
		            	        	  value: new Date(),
		            	        	  format: 'Y-m-d'
		            	          },	            	         
		            	          {
		            	        	  xtype:'combobox',
		            	        	  typeAhead: true,
		            	        	  triggerAction: 'all',
		            	        	  selectOnTab: true,
		            	        	  store: 'casePosition',
		            	        	  displayField:'description',
		            	        	  name:'casePosition',
		            	        	  valueField:'id',
		            	        	  fieldLabel: 'Case Position',
		            	        	  listeners:{
		            	        		  scope: this,
		            	        		  'render': function(cmb, recs, idx) {
		            	        			  cmb.getStore().pageSize='0';
		            	        			  cmb.getStore().currentPage=1;
		            	        			  cmb.getStore().clearFilter();
	  	            	        		  }
		            	        	  }
		            	          },
		            	          {
		            	        	  xtype: 'textfield',
		            	        	  name : 'otherAdvocatePhone',
		            	        	  fieldLabel: 'Phone No of Advocate'
		            	          }
		            	          ]},

		            	          {
		            	        	  items: [
		            	        	          {
		            	        	        	  xtype: 'textfield',
		            	        	        	  name : 'caseNo',
		            	        	        	  fieldLabel: 'Case No'
		            	        	          },
		            	        	          {
		            	        	        	  xtype:'combobox',
		            	        	        	  typeAhead: true,
		            	        	        	  triggerAction: 'all',
		            	        	        	  selectOnTab: true,
		            	        	        	  store: 'branchMaster',
		            	        	        	  displayField:'name',
		            	        	        	  name:'branchName',
		            	        	        	  valueField:'id',
		            	        	        	  fieldLabel: 'Branch Name',
		            	        	        	  listeners:{
		    		            	        		  scope: this,
		    		            	        		  'render': function(cmb, recs, idx) {
		    		            	        			  cmb.getStore().pageSize='0';
		    		            	        			  cmb.getStore().currentPage=1;
		    		            	        			  cmb.getStore().clearFilter();
		    	  	            	        		  }
		    		            	        	  }
		            	        	          },
		            	        	          {
		            	        	        	  xtype:'combobox',
		            	        	        	  typeAhead: true,
		            	        	        	  triggerAction: 'all',
		            	        	        	  selectOnTab: true,
		            	        	        	  store: 'caseNature',
		            	        	        	  displayField:'description',
		            	        	        	  name:'caseNature',
		            	        	        	  valueField:'id',
		            	        	        	  fieldLabel: 'Case Nature',
		            	        	        	  listeners:{
		    		            	        		  scope: this,
		    		            	        		  'render': function(cmb, recs, idx) {
		    		            	        			  cmb.getStore().pageSize='0';
		    		            	        			  cmb.getStore().currentPage=1;
		    		            	        			  cmb.getStore().clearFilter();
		    	  	            	        		  }
		    		            	        	  }
		            	        	          },
		            	        	          {
		            	        	        	  xtype: 'datefield',

		            	        	        	  fieldLabel: 'Date of Judgement',
		            	        	        	  name: 'judgementDate',
		            	        	        	  value: new Date(),
		            	        	        	  format: 'Y-m-d'
		            	        	          }
		            	        	          ,
		            	        	          {
		            	        	        	  xtype: 'textfield',
		            	        	        	  name : 'oldCourtName',
		            	        	        	  fieldLabel: 'Old Court Name, if Any'
		            	        	          },
		            	        	          {
		            	        	        	  xtype:'combobox',
		            	        	        	  typeAhead: true,
		            	        	        	  triggerAction: 'all',
		            	        	        	  selectOnTab: true,
		            	        	        	  store: 'caseForums',
		            	        	        	  displayField:'description',
		            	        	        	  name:'caseForum',
		            	        	        	  valueField:'id',
		            	        	        	  fieldLabel: 'Pending before any other Forum',
		            	        	        	  listeners:{
		    		            	        		  scope: this,
		    		            	        		  'render': function(cmb, recs, idx) {
		    		            	        			  cmb.getStore().pageSize='0';
		    		            	        			  cmb.getStore().currentPage=1;
		    		            	        			  cmb.getStore().clearFilter();
		    	  	            	        		  }
		    		            	        	  }
		            	        	          },

		            	        	          {
		            	        	        	  xtype: 'textfield',
		            	        	        	  name : 'specialOfficeAppointed',
		            	        	        	  fieldLabel: 'Special Officer/ Receiver Appointed'
		            	        	          }]

				            	          }      
				            	          , 
		
				            	          {

		            	        	  items: [
		            	        	          {
		            	        	        	  xtype: 'textfield',
		            	        	        	  name : 'rpmaNo',
		            	        	        	  fieldLabel: 'RP/MA No'
		            	        	          },
		            	        	          {
		            	        	        	  xtype:'combobox',
		            	        	        	  typeAhead: true,
		            	        	        	  triggerAction: 'all',
		            	        	        	  selectOnTab: true,
		            	        	        	  store: 'courtMaster',
		            	        	        	  displayField:'name',
		            	        	        	  name:'courtName',
		            	        	        	  valueField:'id',
		            	        	        	  fieldLabel: 'Court/Tribunal',
		            	        	        	  listeners:{
		    		            	        		  scope: this,
		    		            	        		  'render': function(cmb, recs, idx) {
		    		            	        			  cmb.getStore().pageSize='0';
		    		            	        			  cmb.getStore().currentPage=1;
		    		            	        			  cmb.getStore().clearFilter();
		    	  	            	        		  }
		    		            	        	  }
		            	        	          },
		            	        	          {
		            	        	        	  xtype: 'numberfield',

		            	        	        	  name: 'caseAmount',
		            	        	        	  fieldLabel: 'Suite/Case Amount',
		            	        	        	  value: 99,
		            	        	        	  maxValue: 99,
		            	        	        	  minValue: 0
		            	        	          },
		            	        	          {
		            	        	        	  xtype: 'datefield',

		            	        	        	  fieldLabel: 'Date of Certificate',
		            	        	        	  name: 'certificateDate',
		            	        	        	  value: new Date(),
		            	        	        	  format: 'Y-m-d'
		            	        	          },
		            	        	          {
		            	        	        	  xtype: 'textfield',
		            	        	        	  name : 'oldCourtNumber',
		            	        	        	  fieldLabel: 'Old Court No'
		            	        	          },


		            	        	          {
		            	        	        	  xtype:'combobox',
		            	        	        	  typeAhead: true,
		            	        	        	  triggerAction: 'all',
		            	        	        	  selectOnTab: true,
		            	        	        	  store: 'advocateMaster',
		            	        	        	  displayField:'advName',
		            	        	        	  name:'otherAdvocateName',
		            	        	        	  valueField:'id',
		            	        	        	  fieldLabel: 'Opposite Advocate',
		            	        	        	  listeners:{
		    		            	        		  scope: this,
		    		            	        		  'render': function(cmb, recs, idx) {
		    		            	        			  cmb.getStore().pageSize='0';
		    		            	        			  cmb.getStore().currentPage=1;
		    		            	        			  cmb.getStore().clearFilter();
		    	  	            	        		  }
		    		            	        	  }
		            	        	          },

		            	        	          {
		            	        	        	  xtype: 'datefield',
		            	        	        	  fieldLabel: 'Date of Transfer from Court to Court, if Any',
		            	        	        	  name: 'caseTransferDate',
		            	        	        	  value: new Date(),
		            	        	        	  format: 'Y-m-d'
		            	        	          }
		            	        	          ]
		            	          }];
		  this.buttons = [
			                {
			                	text: 'Save',
			                	action: 'update'
			                },
			                {
			                	type: 'reset',
			                	text: 'Reset',
			                	scope: this,
			                	clickEvent: '_nothing_'
			                	
			                }
			                ];
		

		this.callParent(arguments);
	}
});


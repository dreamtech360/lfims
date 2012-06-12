Ext.define('lfims.view.caseMaster.ImportantDocumentDetails', {
	extend: 'Ext.form.Panel',
	bodyStyle: 'padding:15px 15px;',
	alias : 'widget.ImportantDocumentDetails',
	title : 'Important Document Details',
	fieldDefaults: {
		labelAlign: 'top',
		msgTarget: 'side'
		
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
		            	        	  xtype: 'datefield',

		            	        	  fieldLabel: 'Date of Filing',
		            	        	  name: 'filingDate',
		            	        	  value: new Date()
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
	        						    store: 'casePosition',
	        						    displayField:'name',
	        						    name:'casePosition',
	        						    valueField:'code',
	        						    fieldLabel: 'Case Position'
		            	          },
		            	          {
		            	        	  xtype:'combobox',
	        						    typeAhead: true,
	        						    triggerAction: 'all',
	        						    selectOnTab: true,
	        						    store: 'caseForums',
	        						    displayField:'name',
	        						    name:'caseForum',
	        						    valueField:'code',
	        						    fieldLabel: 'Pending before any other Forum'
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
										store: 'bankMaster',
										displayField:'name',
										name:'bankName',
										valueField:'id',
										fieldLabel: 'Applicant Name'
										
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
										fieldLabel: 'Branch Name'
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
										fieldLabel: 'Court/Tribunal'
									},
									{
										xtype: 'datefield',
										fieldLabel: 'Limit Date, if Any',
										name: 'limitDate',
										value: new Date()
									}
									
									
									]

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
		            	        	  store: 'caseNature',
		            	        	  displayField:'name',
		            	        	  name:'caseNature',
		            	        	  valueField:'code',
		            	        	  fieldLabel: 'Case Nature'
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

		            	        	  fieldLabel: 'Date of Judgement',
		            	        	  name: 'judgementDate',
		            	        	  value: new Date()
		            	          }
		            	          ,
		            	          {
		            	        	  xtype: 'datefield',

		            	        	  fieldLabel: 'Date of Certificate',
		            	        	  name: 'certificateDate',
		            	        	  value: new Date()
		            	          },
		            	          {
		            	        	  xtype: 'datefield',
		            	                     fieldLabel: 'Date of Transfer from Court to Court, if Any',
		            	              name: 'caseTransferDate',
		            	              value: new Date()
		            	          }
		            	          ]
		            	          },
		            	          
		            	          {
		            	        	  items: [
		            	          {
		            	        	  xtype: 'datefield',

		            	        	  fieldLabel: 'Date of Closing',
		            	        	  name: 'closingDate',
		            	        	  value: new Date()
		            	          },
		            	          {
		            	        	  xtype: 'textfield',
		            	        	  name : 'oldCourtName',
		            	        	  fieldLabel: 'Old Court Name, if Any'
		            	          },
		            	          {
		            	        	  xtype: 'textfield',
		            	        	  name : 'address',
		            	        	  fieldLabel: 'Address'
		            	          },
		            	          {
		            	        	  xtype:'combobox',
	        						    typeAhead: true,
	        						    triggerAction: 'all',
	        						    selectOnTab: true,
	        						    store: 'advocateMaster',
	        						    displayField:'name',
	        						    name:'otherAdvocateName',
	        						    valueField:'id',
	        						    fieldLabel: 'Opposite Advocate'
		            	          }
            	                 ,
            	                 {
		            	        	  xtype: 'textfield',
		            	        	  name : 'otherAdcocatePhone',
		            	        	  fieldLabel: 'Phone No of Advocate'
		            	          },
		            	          {
		            	        	  xtype: 'textfield',
		            	        	  name : 'specialOfficeAppointed',
		            	        	  fieldLabel: 'Special Officer/ Receiver Appointed'
		            	          }
		            	          ]}


		            	          ];

		this.buttons = [
		                {
		                	text: 'Save',
		                	action: 'update'
		                },
		                {
		                	text: 'Cancel',
		                	scope: this,
		                	handler: this.close
		                }
		                ];

		this.callParent(arguments);
	}
});


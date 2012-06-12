//GLOBAL VARIABLES
var itemsPerPage = 15;

Ext.onReady(function(){
	Ext.Loader.setConfig({enabled:true});
});

Ext.application({
	controllers:['bankMaster',
	             'branchMaster',
	             'advocateMaster',
	             'ourAdvocateMaster',
	             'courtMaster',
	             'expensesMaster',
	             'ndpMaster',
	             'caseMgmtMaintenance.caseCompletion',
	             'caseMgmtMaintenance.caseDocument',
	             'caseMgmtMaintenance.caseForums',
	             'caseMgmtMaintenance.caseNature',
	             'caseMgmtMaintenance.casePosition',
	             'caseMaster'
	             ],
	             name: 'lfims',
	             appFolder: 'app',

	             launch: function() {
	            	 Ext.create('Ext.container.Viewport', {
	            		 layout: 'fit',
	            		 collapsible: false,

	            		 items: mainPanel
	            	 });
	             }
});

var menuItemss=Ext.create('Ext.menu.Menu', {
	items: [{
		text: 'regular item 1'
	},{
		text: 'regular item 2'
	},{
		text: 'regular item 3'
	}]
});

var caseManagementMenu=Ext.create('Ext.menu.Menu', {
	items: [{
		text: 'Create Case Details'
	},{
		text: 'Search Case Details'
	}],
	listeners: {
		click: {
			fn: function(){
				
				var view = Ext.widget('CaseDetails');
				//view.setMode('new');
				view.show();
				
			/*	Ext.getCmp('rightPanel').removeAll(true);
				Ext.getCmp('rightPanel').removeDocked(Ext.getCmp('pagination'),true);
				
					Ext.getCmp('rightPanel').add([{xtype: 'CaseDetails', actionType:'CREATE_NEW'}]);*/
					
				
			}
		}
	}
});

var store = Ext.create('Ext.data.TreeStore', {
	root: {
		expanded: true,
		children: [
					{ text: "Case Management", expanded: false, children: [
					                                                       { text: "All Cases", leaf:true},
					                                               		 { text: "Today's Cases", leaf:true},
					                                               		 { text: "Pending Cases", leaf:true}
					                                                           ] 
					         },
		           	{ text: "Maintenance", expanded: false, children: [ 
                                                    { text: "General Maintenance", expanded: false, children: [
                                         		                                                              { text: "Bank Master", leaf: true },
                                        		                                                              { text: "Branch Master", leaf: true},
                                        		                                                              { text: "Advocate Master", leaf: true},
                                        		                                                              { text: "Our Advocate Master", leaf: true},
                                        		                                                              { text: "Court Master", leaf: true},
                                        		                                                              ] 
                                        		           },
                                        		           { text: "Case Management Maintenance", expanded: false, children: [
                                        		                                                                      { text: "Next Date Purpose Master", leaf: true},
                                        		                                                                      { text: "Case Nature", leaf: true},
                                        		                                                                      { text: "Case Position", leaf: true},
                                        		                                                                      { text: "Case Completion Process", leaf: true},
                                        		                                                                      { text: "Forums", leaf: true},
                                        		                                                                      { text: "Case Document Filling", leaf: true}
                                        				                                                              ] 
                                        				    },
                                        				    { text: "Fees Perticulars Maintenance", expanded: false, children: [
                                        				                                                                        { text: "Expenses Master", leaf: true}
                                        					                                                                      
                                        							                                                            ] 
                                        				    }
                                                           ]
		           		},
                  { text: "Reports", expanded: false, children: [
                                                                 { text: "Maintenance Reports", expanded:false, children:[ 
                                                                   { text: "Bank Master Report", leaf: true },
                                                                   { text: "Branch Master Report", leaf: true},
                                                                   { text: "Advocate Master Report", leaf: true},
                                                                   { text: "Our Advocate Master Report", leaf: true},
                                                                   { text: "Court Master Report", leaf: true},
                                                                   { text: "Expenses Master Report", leaf: true},
                                                                   { text: "Next Date Purpose Master Report", leaf: true}] 
                                                                 },
                                                                 { text: "Case Management Reports", expanded:false, children:
                                                                	 [ 
                                                                         { text: "Today's Cases", leaf: true }
                                                                         ] 
                                                                 }
                                                                  ] 
		           }
                  
		         ]
	}
});

var leftPanel=Ext.create('Ext.tree.Panel', {
	title: 'Maintenance',
	store: store,
	rootVisible: false,
	flex:1,
	id: 'leftPanel',
	listeners: {

		itemdblclick: {
			fn: function(view, record, item, index, event) {

				nodeId = record.data.id;
				htmlId = item.id;
				//Ext.Msg.alert('Status', record.get('text'));
				// Ext.getCmp('rightPanel').setTitle(record.get('text'));
				Ext.getCmp('rightPanel').removeAll(true);
				Ext.getCmp('rightPanel').removeDocked(Ext.getCmp('pagination'),true);
				if(record.get('text') == 'Bank Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'bankMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'bankMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Branch Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'branchMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'branchMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Court Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'courtMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'courtMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Advocate Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'advocateMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'advocateMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Our Advocate Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'ourAdvocateMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'ourAdvocateMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Expenses Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'expensesMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'expensesMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Next Date Purpose Master')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'ndpMasterList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'ndpMaster',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Case Nature')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'caseNatureList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'caseNature',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Case Position')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'casePositionList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'casePosition',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Case Completion Process')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'caseCompletionList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'caseCompletion',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Forums')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'caseForumsList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'caseForums',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'Case Document Filling')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'caseDocumentList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'caseDocument',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				else if(record.get('text') == 'All Cases')
				{
					Ext.getCmp('rightPanel').add([{xtype: 'CaseDetailsList'}]);
					Ext.getCmp('rightPanel').addDocked([{
						id:'pagination',
						xtype: 'pagingtoolbar',
						store: 'caseMasterDetails',   // same store GridPanel is using
						dock: 'bottom',
						displayInfo: true,
						flex: 1
					},]);
				}
				 

			}
		}
	}

});

var rightPanel = Ext.create('Ext.panel.Panel', {
	collapsible:false,
	border:true,
	flex : 5,
	autoDestroy: true,
//	title: 'Ext 4 Panel - Header',
//	headerPosition: 'top',
	id : 'rightPanel',
	layout : {
		type : 'fit',
		align : 'stretch'
	},
	dockedItems: [
{
	xtype: 'toolbar',
	dock: 'top',
	items: [{
		xtype: 'button',
		text: 'Case Management',
		menu: caseManagementMenu
	}]
},

{
	xtype: 'toolbar',
	dock: 'bottom',
	items: [{
		xtype: 'component',
		flex: 1 //will occupy 100% of the width of the panel
	},{
		xtype: 'button',
		text: 'Button - Footer Toolbar'
	}]
}

],
listeners: {
	
	render: {
		fn: function( component, eOpts ){
			
			component.removeAll(true);
			component.removeDocked(Ext.getCmp('pagination'),true);
			component.add([{xtype: 'CaseDetailsList'}]);
			component.addDocked([{
				id:'pagination',
				xtype: 'pagingtoolbar',
				store: 'caseMasterDetails',   // same store GridPanel is using
				dock: 'bottom',
				displayInfo: true,
				flex: 1
			},]);
			
		
		}
	}
}

});

var mainPanel=Ext.create('Ext.panel.Panel', {

	layout : {
		type : 'hbox',
		align : 'stretch'

	},
	title : 'Law Firm Information Management System',
	id: 'mainPanel',
	items: [

	        leftPanel,
	        {
	        	xtype: 'splitter',
	        	collapsible: true,
	        	collapseTarget: 'prev'
	        },
	        rightPanel
	        ]
});
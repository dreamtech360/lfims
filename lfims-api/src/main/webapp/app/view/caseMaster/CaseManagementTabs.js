Ext.define('lfims.view.caseMaster.CaseManagementTabs', {
	extend: 'Ext.tab.Panel',
	alias : 'widget.CaseManagementTabs',
	config: {
		actionType:'CREATE_NEW'
	},
	
	  items: [{
		  xtype: 'CaseMasterDetails'
	  }
	  , {
	        title: 'Defendent Details',
	      /*  tabConfig: {
	            title: 'Custom Title',
	            tooltip: 'A button tooltip'
	        }*/
	    },
	    {
	        title: 'Security Details'
	    },{
	        title: 'Important Documents'
	    }
	    
	    ]
});

Ext.define('lfims.store.ourAdvocateMaster', {
	extend: 'Ext.data.Store',
	model: 'lfims.model.ourAdvocateMaster',
	remoteFilter:true,
	pageSize: itemsPerPage,
	proxy: {
		type: 'rest',
		url: 'http://localhost:8080/lfims/webresources/maintenance/ouradvocates',
		noCache: false,
		reader: {
			type: 'json',
			root: 'ourAdvocateMaster',
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

	},
	listeners:{

		update: function(store,operation,op){
			console.log('update called');
			store.sync();
		},
		remove: function(store,operation,op){
		//	console.log('remove called');
		//	store.sync();
		//	store.load();
		},
		write: function(store,operation,op){
			console.log('write called');
			store.load();
		}

	}



});


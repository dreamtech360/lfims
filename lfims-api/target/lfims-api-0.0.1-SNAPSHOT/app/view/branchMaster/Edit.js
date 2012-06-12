Ext.define('lfims.view.branchMaster.Edit', {
    extend: 'Ext.window.Window',
    alias : 'widget.branchMasterEdit',
    title : 'Edit Branch Details',
    layout: 'fit',
    autoShow: true,

    initComponent: function() {
        this.items = [
            {
            
          
            
                xtype: 'form',
                items: [
					{xtype:'combobox',
					    typeAhead: true,
					    triggerAction: 'all',
					    selectOnTab: true,
					    store: 'bankMaster',
					    displayField:'name',
					    name:'bankName',
					    valueField:'id',
					    fieldLabel: 'Bank Name'
					},
                    {
                        xtype: 'textfield',
                        name : 'name',
                        fieldLabel: 'Branch Name'
                    },
                    {
                        xtype: 'textfield',
                        name : 'region',
                        fieldLabel: 'Region'
                    },
                    {
                        xtype: 'textfield',
                        name : 'address',
                        fieldLabel: 'Address'
                    },
                    {
                        xtype: 'textfield',
                        name : 'contactPerson',
                        fieldLabel: 'Contact Person'
                    },
                    {
                        xtype: 'textfield',
                        name : 'contactPhone',
                        fieldLabel: 'Contact Phone'
                    }
                ]
            }
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
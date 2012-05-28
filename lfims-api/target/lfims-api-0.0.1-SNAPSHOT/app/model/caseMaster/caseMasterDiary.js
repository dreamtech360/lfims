Ext.define('lfims.model.caseMaster.caseMasterDiary', {
    extend: 'Ext.data.Model',
   
    fields: [ 
             'id',
         	'uuid',
         	'hearingDate',
         	'beforeBenchName',
         	'ourAdvocateName',
         	'advocateName',
         	'ourWitnessDetails',
         	'othersWitnessDetails',
         	'ourDocuments',
         	'othersDocuments',
         	'caseCompletionMethod',
         	'otherDetails',
         	'quicky',
         	'nextHearingDate',
         	'nextDatePurpose'
             ],
    idProperty: 'id'
});
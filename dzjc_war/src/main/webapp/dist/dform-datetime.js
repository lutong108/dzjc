JSONEditor.defaults.editors.datetime = JSONEditor.defaults.editors.string.extend({
    getValue: function() {
    	//获取datetimePicker对象
    	var d = $(this.input).data("DateTimePicker");
    	if(d&&d.date()){
    		//获取毫秒数
//    		var time = d.date().toDate().getTime();
    		var time = d.date().format('YYYY-MM-DD HH:mm');
    		return time;
    	}
    	return this.value;
    },

    setValue: function(val) {
    	var d = $(this.input).data("DateTimePicker");
    	if(d){
    		var tv = this.value;
//    		var v = d.date().format('YYYY-MM-DD HH:mm');
    		if(tv !== val){
    			d.date(val);
    			this.value = val;
    		}
    	}
    },

    build: function() {
        this._super();
        var _this = this;
        defaultDate = new Date();
        $(this.input).datetimepicker({
                format: 'YYYY-MM-DD HH:mm',
//                defaultDate: defaultDate,
                locale: 'zh-cn'
            })
            .on('dp.change', function(e) {
//                var path = $(this).prop("name").replace(/\[/g, ".").replace(/\]/g, "");
//                var path = _this.path;
//                editor.getEditor(path).setValue($(this).val());
                var v = e.date.format('YYYY-MM-DD HH:mm');
//                editor.getEditor(path).setValue(v);
                var editorValue = editor.getValue();
                editorValue[_this.key] = v;
                editor.setValue(editorValue);
            });
    }
});


//Instruct the json-editor to use the custom datetime-editor.
JSONEditor.defaults.resolvers.unshift(function(schema) {
    if (schema.type === "string" && schema.format === "datetime") {
        return "datetime";
    }
});

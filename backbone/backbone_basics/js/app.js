
Backbone.emulateHTTP=true;
Backbone.emulateJSON=true;
var Model = Backbone.Model.extend({
	});

var BookmarkModel= Model.extend({
	defaults:{
	
	},
	
	initialize:function(){
	
	},
	//url:"api/Bookmark.php",
	urlRoot:"api/Bookmark.php"
});
var TagModel=Model.extend({
	defaults:{
	
	},
	
	initialize:function(){
	
	},
	url:"api/TagList.php"
});

var Collection=Backbone.Collection.extend({
	model:Model
});

var BookmarkListCollection=Collection.extend({
	initialize:function(){
		this.fetch({async:false});
	},

	renderByTag:function(tag){
		filtered = this.filter(function(bookmarkModel){
			this.tags = new BookmarkTagListCollection();
	    	this.tags.url = "api/tagList.php?id_bookmark=" + bookmarkModel.id;
	    	this.tags.fetch({async:false});
	    	var flag=false;
	    	_.each(this.tags.models, function (item) {
            	flag=flag|| (item.get("tag")===tag);
       		}, this);


			return flag;
		});
		return new BookmarkListCollection(filtered);
	},
	model:BookmarkModel,
	url:"api/BookmarkList.php"
});

var BookmarkTagListCollection=Collection.extend({
	model:TagModel,
	url:"api/tagList.php?id_bookmark=1"
});

var TagListCollection = Collection.extend({
	initialize:function(){
		this.fetch({async:false});
	},
	model:TagModel,
	url:"api/TagCountList.php"
})

var View=Backbone.View.extend({

});

var BookmarkView=View.extend({
	tagName:"li",
	className: "contact-container",

	initialize: function () {
 
        },
	template:$("#bookmarkViewTemplate").html(),
	template2:$("#bookmarkTagsViewTemplate").html(),
	render:function(){
        var tmpl = _.template(this.template);
            
        $(this.el).html(tmpl(this.model.toJSON()));

        this.tags = new BookmarkTagListCollection();
	    this.tags.url = "api/tagList.php?id_bookmark=" + this.model.id;
	    this.tags.fetch({async:false}); 
        this.renderTags();

        return this;
	},


	renderTags: function () {
        _.each(this.tags.models, function (item) {
            this.renderTag(item);
        }, this);
    },

    renderTag: function (item) {
    	var tmpl2 = _.template(this.template2);
		$(this.el).append(tmpl2(item.toJSON()));
    }
});


var TagView=View.extend({
	initialize: function () {
            
        },
    className: "contact-container",
	tagName:"li",
	template:$("#tagViewTemplate").html(),
	render:function(){
		var tmpl = _.template(this.template);
		$(this.el).html(tmpl(this.model.toJSON()));
		return this;
	}
});

var MasterView = Backbone.View.extend({
    el: $("#bookmarkList"),
    
    initialize: function () {
        this.collection = new BookmarkListCollection();
        this.render();
    },

    renderByTag:function(tag){
    	this.collection = new BookmarkListCollection();
    	this.collection = this.collection.renderByTag(tag);
    	this.$el.empty();
    	$("#bookmarkTagFilter").empty().append(tag);
        _.each(this.collection.models, function (item) {
            this.renderBookmark(item);
        }, this);
    },

    render: function () {
    	this.$el.empty();
    	this.collection = new BookmarkListCollection();
    	$("#bookmarkTagFilter").empty().append("none");
        _.each(this.collection.models, function (item) {
            this.renderBookmark(item);
        }, this);
    },

    renderBookmark: function (item) {
        var bookmarkView = new BookmarkView({
            model: item
        });
		this.$el.append(bookmarkView.render().el)
    },
	
	saveBookmark: function (){
		var bookmark = new BookmarkModel();
		bookmark.set({title: $("#title").val()});
		bookmark.set({url: $("#url").val()});
		bookmark.set({tags:$("#tags").val()});
		bookmark.save();
		bookmark.fetch();
		this.render();
		tagMasterView.render();
	},
	
	deleteBookmarkModel:function(idBookmark){
		var model=this.collection.get(idBookmark);
		model.destroy({wait: true});
		this.render();
		tagMasterView.render();
	}

});


var TagMasterView =Backbone.View.extend({
    el: $("#tagCountList"),
    initialize: function () {
        this.collection = new TagListCollection();
        this.render();
    },

    render:function(){
		this.$el.empty();
		this.collection.fetch();
    	_.each(this.collection.models, function (item) {
            this.renderTag(item);
        }, this);
    },

    renderTag:function(item){
    	var tagView = new TagView({
            model: item
        });
		this.$el.append(tagView.render().el)
    }

});




var Routers=Backbone.Router.extend({
	
});

var Router=Routers.extend({
	
	routes:{
		"load":"_loadBookmarkListCollection"
	},
	
	_loadBookmarkListCollection:function(){
		var bookmarkModelList = new BookmarkListCollection();

   		var bookmarkView = new BookmarkView({model:bookmarkModel});
    		bookmarkModel.fetch({
        	success: function () {
           		 $('bookmarkList').html(bookmarkView.render().el);
       		 }
    	});
	}

});


var masterView;
var tagMasterView;
$(function() {
    //create instance of master view
    masterView = new MasterView();
    tagMasterView = new TagMasterView();
});


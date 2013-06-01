$(document).ready(function() {
	$(".btn.delete").click(function(event){
		event.preventDefault();
		var action = this.href
		$("#deletePostForm").attr('action', action)
		$("#confirmDeleteModal").modal('show')
	})
});
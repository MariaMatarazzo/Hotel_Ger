$(document).ready(function() {
loadRoom();

$('#roomForm').submit(function(event) {
event.preventDefault();
const id = $('#roomId').val();
if (id) {
updateRoom(id);
} else {
addRoom();
}
});
});

function loadRoom() {
$.getJSON('/quarto', function(data) {
$('#roomTableBody').empty();
data.forEach(room => {
$('#roomTableBody').append(
`<tr>
<td>${room.id}</td>
<td>${room.num}</td>
<td>${room.tipo}</td>
<td>
<button class="btn btn-sm btn-warning" onclick="showEditRoomForm(${room.id}, '${room.num}', '${room.tipo}')">Edit</button>
<button class="btn btn-sm btn-danger" onclick="deleteRoom(${room.id})">Delete</button>
</td>
</tr>`
);
});
});
}

function showAddRoomForm() {
$('#formTitle').text('Add Quarto');
$('#roomId').val('');
$('#roomNumber').val('');
$('#roomType').val('');
$('#roomFormModal').show();
}

function showEditRoomForm(id, num, tipo) {
$('#formTitle').text('Edit Quarto');
$('#roomId').val(id);
$('#roomNumber').val(num);
$('#roomType').val(tipo);
$('#roomFormModal').show();
}

function closeRoomForm() {
$('#roomFormModal').hide();
}

function addRoom() {
const room = {
num: $('#roomNumber').val(),
tipo: $('#roomType').val(),
};
$.ajax({
url: '/quarto',
type: 'POST',
contentType: 'application/json',
data: JSON.stringify(room),
success: function() {
closeRoomForm();
loadRoom();
}
});
}

function updateRoom(id) {
const room = {
num: $('#roomNumber').val(),
tipo: $('#roomType').val(),
};
$.ajax({
url: `/quarto/${id}`,
type: 'PUT',
contentType: 'application/json',
data: JSON.stringify(room),
success: function() {
closeRoomForm();
loadRoom();
}
});
}

function deleteRoom(id) {
if (confirm('Realmente vai deletar?')) {
$.ajax({
url: `/quarto/${id}`,
type: 'DELETE',
success: function() {
loadRoom();
}
});
}
}
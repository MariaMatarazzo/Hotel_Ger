$(document).ready(function () {
loadReservations();

$("#reservationForm").submit(function (event) {
event.preventDefault();
const id = $("#reservationId").val();
if (id) {
updateReservation(id);
} else {
addReservation();
}
});
});

function loadReservations() {
$.getJSON('/reserva', function (data) {
$('#reservationTableBody').empty();
data.forEach(reservation => {
$('#reservationTableBody').append(
`<tr>
<td>${reservation.hospede.name}</td>
<td>${reservation.quarto.num}</td>
<td>${reservation.checkInData}</td>
<td>${reservation.checkOutData}</td>
<td>
<button class="btn btn-sm btn-warning" onclick="loadReservationForEdit(${reservation.id})">Edit</button>
<button class="btn btn-sm btn-danger" onclick="deleteReservation(${reservation.id})">Delete</button>
</td>
</tr>`
);
});
});
}

function carregarHospedes() {
$.ajax({
url: '/hospede',
type: 'GET',
success: function (data) {
var reservationGuest = $("#reservationGuest");
reservationGuest.empty();
data.forEach(function (hospede) {
var option = "<option value='" + hospede.id + "'>" + hospede.name + "</option>";
reservationGuest.append(option);
});
},
error: function (error) {
console.log("Erro ao carregar hóspedes:", error);
}
});
}

function carregarQuartos() {
$.ajax({
url: '/quarto',
type: 'GET',
success: function (data) {
var reservationRoom = $("#reservationRoom");
reservationRoom.empty();
data.forEach(function (quarto) {
var option = "<option value='" + quarto.id + "'>" + quarto.num + "</option>";
reservationRoom.append(option);
});
},
error: function (error) {
console.log("Erro ao carregar quartos:", error);
}
});
}

function showAddReservationForm() {
$("#reservationForm")[0].reset();
$("#formTitle").text('Add Reserva');
$("#reservationId").val("");
$("#reservationGuest").val("");
$("#reservationRoom").val("");
$("#reservationCheckIn").val("");
$("#reservationCheckOut").val("");
$("#reservationFormModal").show();

// Carregar hóspedes e quartos quando o formulário for exibido
carregarHospedes();
carregarQuartos();
}

function loadReservationForEdit(id) {
$.getJSON(`/reserva/${id}`, function (reservation) {
showEditReservationForm(reservation.id, reservation.hospede.id, reservation.quarto.id, reservation.checkInData, reservation.checkOutData);
});
}

function showEditReservationForm(id, guestId, roomId, checkInDate, checkOutDate) {
$("#formTitle").text('Edit Reserva');
$("#reservationId").val(id);
$("#reservationGuest").val(guestId);
$("#reservationRoom").val(roomId);
$("#reservationCheckIn").val(checkInDate);
$("#reservationCheckOut").val(checkOutDate);
$("#reservationFormModal").show();

// Carregar hóspedes e quartos quando o formulário for exibido
carregarHospedes();
carregarQuartos();
}

function closeReservationForm() {
$("#reservationFormModal").hide();
}

function addReservation() {
const reservation = {
hospede: { id: $("#reservationGuest").val() },
quarto: { id: $("#reservationRoom").val() },
checkInData: $("#reservationCheckIn").val(),
checkOutData: $("#reservationCheckOut").val()
};
$.ajax({
url: '/reserva',
type: 'POST',
contentType: 'application/json',
data: JSON.stringify(reservation),
success: function () {
closeReservationForm();
loadReservations();
}
});
}

function updateReservation(id) {
const reservation = {
hospede: { id: $("#reservationGuest").val() },
quarto: { id: $("#reservationRoom").val() },
checkInData: $("#reservationCheckIn").val(),
checkOutData: $("#reservationCheckOut").val()
};
$.ajax({
url: `/reserva/${id}`,
type: 'PUT',
contentType: 'application/json',
data: JSON.stringify(reservation),
success: function () {
closeReservationForm();
loadReservations();
}
});
}

function deleteReservation(id) {
if (confirm("Deseja excluir a reserva?")) {
$.ajax({
url: `/reserva/${id}`,
type: 'DELETE',
success: function () {
loadReservations();
}
});
}
}

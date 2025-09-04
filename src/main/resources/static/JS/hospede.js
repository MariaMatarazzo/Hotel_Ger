$(document).ready(function () {
  // Carrega hóspedes ao abrir a página
  loadGuests();

  // Captura o submit do formulário
  $("#guestForm").submit(function (event) {
    event.preventDefault();

    const id = $("#guestId").val();

    if (id) {
      updateGuest(id);
    } else {
      addGuest();
    }
  });
});

// Função para mostrar o formulário de inclusão
function showAddGuestForm() {
  $("#guestId").val(""); // limpa id (modo adicionar)
  $("#guestForm")[0].reset(); // limpa formulário
  $("#formTitle").text("Incluir Hóspede");
  $("#guestFormModal").show();
}

// Função para mostrar formulário de edição
function showEditGuestForm(id, name, email, telefone) {
  $("#formTitle").text("Editar Hóspede");
  $("#guestId").val(id);
  $("#guestName").val(name);
  $("#guestEmail").val(email);
  $("#guestTelefone").val(telefone);
  $("#guestFormModal").show();
}

// Função para fechar o formulário
function closeGuestForm() {
  $("#guestFormModal").hide();
}

// Carregar lista de hóspedes
function loadGuests() {
  $.getJSON("/hospede", function (data) {
    $("#guestTableBody").empty();
    data.forEach((guest) => {
      $("#guestTableBody").append(`
        <tr>
          <td>${guest.id}</td>
          <td>${guest.name}</td>
          <td>${guest.email}</td>
          <td>${guest.telefone}</td>
          <td>
            <button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${guest.id}, '${guest.name}', '${guest.email}', '${guest.telefone}')">Editar</button>
            <button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Excluir</button>
          </td>
        </tr>
      `);
    });
  });
}

// Adicionar hóspede
function addGuest() {
  const guest = {
    name: $("#guestName").val(),
    email: $("#guestEmail").val(),
    telefone: $("#guestTelefone").val(),
  };

  $.ajax({
    url: "/hospede",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(guest),
    success: function () {
      closeGuestForm();
      loadGuests();
    },
  });
}

// Atualizar hóspede
function updateGuest(id) {
  const guest = {
    name: $("#guestName").val(),
    email: $("#guestEmail").val(),
    telefone: $("#guestTelefone").val(),
  };

  $.ajax({
    url: `/hospede/${id}`,
    type: "PUT",
    contentType: "application/json",
    data: JSON.stringify(guest),
    success: function () {
      closeGuestForm();
      loadGuests();
    },
  });
}

// Deletar hóspede
function deleteGuest(id) {
  if (confirm("Realmente deseja deletar este hóspede?")) {
    $.ajax({
      url: `/hospede/${id}`,
      type: "DELETE",
      success: function () {
        loadGuests();
      },
    });
  }
}

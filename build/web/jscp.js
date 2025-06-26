// Variables globales
let currentUser = null;
let cart = [];

// Inicialización al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    loadUser();
    loadCart();
    updateUI();
});

// Funciones para el menú móvil
function toggleMenu() {
    const navLinks = document.getElementById('navLinks');
    navLinks.classList.toggle('active');
}

// Scroll suave
function scrollToSection(sectionId) {
    const element = document.getElementById(sectionId);
    const offsetTop = element.offsetTop - 80;
    window.scrollTo({
        top: offsetTop,
        behavior: 'smooth'
    });
    
    // Cerrar menú móvil si está abierto
    const navLinks = document.getElementById('navLinks');
    navLinks.classList.remove('active');
}

function scrollToTop() {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
}

// Funciones para el carrito de compras
function toggleCart() {
    const cartSidebar = document.getElementById('cartSidebar');
    cartSidebar.classList.toggle('active');
}

function addToCart(productName, price) {
  // Obtener el carrito actual
  let items = JSON.parse(localStorage.getItem('cartItems')) || [];
  let total = parseInt(localStorage.getItem('cartTotal')) || 0;

  // Agregar nuevo producto
  items.push({ name: productName, price: price });
  total += price;

  // Guardar nuevamente en localStorage
  localStorage.setItem('cartItems', JSON.stringify(items));
  localStorage.setItem('cartTotal', total);

  // Actualizar contador visual si existe
  const cartCount = document.getElementById("cartCount");
  if (cartCount) cartCount.textContent = items.length;
}


function removeFromCart(index) {
    cart.splice(index, 1);
    saveCart();
    updateCartUI();
}

function updateQuantity(index, change) {
    const newQuantity = cart[index].quantity + change;
    
    if (newQuantity < 1) {
        removeFromCart(index);
    } else {
        cart[index].quantity = newQuantity;
        saveCart();
        updateCartUI();
    }
}

function saveCart() {
    localStorage.setItem('oxifatCart', JSON.stringify(cart));
}

function loadCart() {
    const savedCart = localStorage.getItem('oxifatCart');
    if (savedCart) {
        cart = JSON.parse(savedCart);
    }
}

function updateCartUI() {
    const cartItemsElement = document.getElementById('cartItems');
    const cartCountElement = document.getElementById('cartCount');
    const cartTotalElement = document.getElementById('cartTotal');
    
    // Actualizar contador
    const totalItems = cart.reduce((total, item) => total + item.quantity, 0);
    cartCountElement.textContent = totalItems;
    
    // Actualizar lista de productos
    cartItemsElement.innerHTML = '';
    
    let total = 0;
    
    cart.forEach((item, index) => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        
        const itemElement = document.createElement('div');
        itemElement.className = 'cart-item';
        itemElement.innerHTML = `
            <div class="cart-item-info">
                <h4>${item.name}</h4>
                <p>$${item.price} MXN x ${item.quantity}</p>
            </div>
            <div class="cart-item-actions">
                <button onclick="updateQuantity(${index}, -1)">-</button>
                <span>${item.quantity}</span>
                <button onclick="updateQuantity(${index}, 1)">+</button>
                <button onclick="removeFromCart(${index})">🗑️</button>
            </div>
        `;
        
        cartItemsElement.appendChild(itemElement);
    });
    
    // Actualizar total
    cartTotalElement.textContent = total;
}

function checkout() {
    if (cart.length === 0) {
        alert('Tu carrito está vacío');
        return;
    }
    
    if (!currentUser) {
        alert('Por favor inicia sesión para continuar con la compra');
        openModal();
        return;
    }
    
    // Aquí normalmente se procesaría el pago
    alert(`Compra realizada por $${document.getElementById('cartTotal').textContent} MXN. Gracias por tu compra, ${currentUser.name}!`);
    cart = [];
    saveCart();
    updateCartUI();
    toggleCart();
}

// Funciones para autenticación
function openModal() {
    document.getElementById('authModal').classList.add('active');
}

function closeModal() {
    document.getElementById('authModal').classList.remove('active');
}

function showLoginForm() {
    document.getElementById('loginForm').style.display = 'block';
    document.getElementById('registerForm').style.display = 'none';
}

function showRegisterForm() {
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('registerForm').style.display = 'block';
}

function login(event) {
    event.preventDefault();
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;
    
    // Validación básica (en un caso real, esto vendría de una base de datos)
   
    const user = users.find(u => u.email === email && u.password === password);
    
    if (user) {
        currentUser = user;
        localStorage.setItem('oxifatCurrentUser', JSON.stringify(currentUser));
        closeModal();
        updateUI();
        alert(`Bienvenido de nuevo, ${user.name}!`);
    } else {
        alert('Correo electrónico o contraseña incorrectos');
    }
}

function register(event) {
    event.preventDefault();
    const name = document.getElementById('registerName').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;
    
    // Validación básica
    if (!name || !email || !password) {
        alert('Por favor completa todos los campos');
        return;
    }
    
    
    // Verificar si el usuario ya existe
    if (users.some(u => u.email === email)) {
        alert('Este correo electrónico ya está registrado');
        return;
    }
    
    // Crear nuevo usuario
    const newUser = { name, email, password };
    users.push(newUser);
    localStorage.setItem('oxifatUsers', JSON.stringify(users));
    
    // Iniciar sesión automáticamente
    currentUser = newUser;
    localStorage.setItem('oxifatCurrentUser', JSON.stringify(currentUser));
    closeModal();
    updateUI();
    alert(`¡Bienvenido a OxiFat, ${name}!`);
}

function logout() {
    currentUser = null;
    localStorage.removeItem('oxifatCurrentUser');
    updateUI();
    alert('Has cerrado sesión correctamente');
}

function loadUser() {
    const savedUser = localStorage.getItem('oxifatCurrentUser');
    if (savedUser) {
        currentUser = JSON.parse(savedUser);
    }
}

function updateUI() {
    const userSection = document.getElementById('userSection');
    
    if (currentUser) {
        userSection.innerHTML = `
            <a href="#" class="user-btn" onclick="logout()">
                👤 ${currentUser.name.split(' ')[0]}
            </a>
        `;
    } else {
        userSection.innerHTML = `
            <a href="#" class="signin-btn" onclick="openModal()">
                Iniciar Sesión
            </a>
        `;
    }
}
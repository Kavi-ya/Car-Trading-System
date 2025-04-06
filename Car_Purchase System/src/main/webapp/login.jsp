<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - CarTrader</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #3a86ff;
            --secondary-color: #ff006e;
            --dark-color: #212529;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background-image: url('https://images.unsplash.com/photo-1557702899-2e98d8bf3d7a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1650&q=80');
            background-size: cover;
            background-position: center;
            padding: 20px;
        }
        
        .login-container {
            width: 100%;
            max-width: 450px;
            padding: 0;
            margin: 0 auto;
        }
        
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            overflow: hidden;
            background-color: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(5px);
        }
        
        .card-header {
            background-color: var(--primary-color);
            color: white;
            text-align: center;
            padding: 25px 20px;
            border-bottom: none;
        }
        
        .card-header .logo {
            font-size: 1.8rem;
            font-weight: bold;
            margin-bottom: 10px;
        }
        
        .card-header p {
            margin-bottom: 0;
            opacity: 0.9;
        }
        
        .card-body {
            padding: 30px;
        }
        
        .form-floating {
            margin-bottom: 20px;
        }
        
        .form-floating label {
            color: #6c757d;
        }
        
        .form-control:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.25rem rgba(58, 134, 255, 0.25);
        }
        
        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
            padding: 12px;
            font-weight: 600;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        
        .btn-primary:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        }
        
        .divider {
            text-align: center;
            margin: 20px 0;
            position: relative;
        }
        
        .divider:before {
            content: '';
            position: absolute;
            top: 50%;
            left: 0;
            width: 45%;
            height: 1px;
            background-color: #dee2e6;
        }
        
        .divider:after {
            content: '';
            position: absolute;
            top: 50%;
            right: 0;
            width: 45%;
            height: 1px;
            background-color: #dee2e6;
        }
        
        .divider span {
            display: inline-block;
            padding: 0 10px;
            background-color: #fff;
            position: relative;
            z-index: 1;
            color: #6c757d;
        }
        
        .social-login {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-bottom: 20px;
        }
        
        .social-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            border: 1px solid #dee2e6;
            font-size: 1.2rem;
            transition: transform 0.3s, background-color 0.3s;
        }
        
        .social-btn:hover {
            transform: translateY(-3px);
        }
        
        .facebook {
            color: #3b5998;
        }
        
        .facebook:hover {
            background-color: #3b5998;
            color: white;
        }
        
        .google {
            color: #db4437;
        }
        
        .google:hover {
            background-color: #db4437;
            color: white;
        }
        
        .twitter {
            color: #1da1f2;
        }
        
        .twitter:hover {
            background-color: #1da1f2;
            color: white;
        }
        
        .forgot-password {
            text-align: center;
            margin-top: 15px;
        }
        
        .forgot-password a {
            color: var(--primary-color);
            text-decoration: none;
            transition: color 0.3s;
        }
        
        .forgot-password a:hover {
            color: #1a56cc;
            text-decoration: underline;
        }
        
        .register-link {
            text-align: center;
            padding: 20px;
            background-color: #f8f9fa;
            border-top: 1px solid #dee2e6;
        }
        
        .register-link a {
            color: var(--primary-color);
            font-weight: 600;
            text-decoration: none;
        }
        
        .register-link a:hover {
            text-decoration: underline;
        }
        
        .input-group-text {
            cursor: pointer;
            background-color: white;
        }
        
        .error-message {
            color: #dc3545;
            font-size: 0.875rem;
            margin-top: 5px;
            display: none;
        }
        
        .success-message {
            color: #198754;
            text-align: center;
            display: none;
            margin-bottom: 15px;
        }
        
        .error-alert {
            color: #dc3545;
            text-align: center;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 5px;
            background-color: rgba(220, 53, 69, 0.1);
            border: 1px solid rgba(220, 53, 69, 0.2);
        }
        
        .floating-back {
            position: absolute;
            top: 20px;
            left: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 50%;
            width: 45px;
            height: 45px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: var(--dark-color);
            text-decoration: none;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, background-color 0.3s;
        }
        
        .floating-back:hover {
            transform: translateY(-3px);
            background-color: white;
        }
        
        /* Animation classes */
        .slide-up {
            animation: slideUp 0.5s ease forwards;
        }
        
        @keyframes slideUp {
            0% {
                opacity: 0;
                transform: translateY(20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }
        
        .fade-in {
            animation: fadeIn 0.5s ease forwards;
        }
        
        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }
        
        .shake {
            animation: shake 0.5s cubic-bezier(.36,.07,.19,.97) both;
        }
        
        @keyframes shake {
            10%, 90% { transform: translate3d(-1px, 0, 0); }
            20%, 80% { transform: translate3d(2px, 0, 0); }
            30%, 50%, 70% { transform: translate3d(-4px, 0, 0); }
            40%, 60% { transform: translate3d(4px, 0, 0); }
        }
    </style>
</head>
<body>
    <a href="index.jsp" class="floating-back">
        <i class="fas fa-arrow-left"></i>
    </a>
    
    <div class="login-container">
        <div class="card slide-up">
            <div class="card-header">
                <div class="logo">
                    <i class="fas fa-car-side me-2"></i>CarTrader
                </div>
                <p>Welcome back! Please login to your account</p>
            </div>
            <div class="card-body">
                <!-- Success message -->
                <div class="success-message fade-in" id="successMessage">
                    <i class="fas fa-check-circle me-2"></i>Login successful! Redirecting...
                </div>
                
                <!-- Error message from servlet -->
                <% if(request.getParameter("error") != null) { %>
                    <div class="error-alert fade-in">
                        <i class="fas fa-exclamation-circle me-2"></i>
                        <% if(request.getParameter("error").equals("invalid")) { %>
                            Invalid email or password. Please try again.
                        <% } else if(request.getParameter("error").equals("notfound")) { %>
                            Account not found. Please register first.
                        <% } else { %>
                            An error occurred. Please try again.
                        <% } %>
                    </div>
                <% } %>
                
                <!-- Login Form with action pointing to LoginServlet -->
                <form id="loginForm" action="LoginServlet" method="post">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="usernameInput" name="username" placeholder="Username or Email" required>
                        <label for="usernameInput"><i class="fas fa-user me-2"></i>Username or Email</label>
                        <div class="error-message" id="usernameError">Please enter your username or email address.</div>
                    </div>
                    
                    <div class="form-floating">
                        <input type="password" class="form-control" id="passwordInput" name="password" placeholder="Password" required>
                        <label for="passwordInput"><i class="fas fa-lock me-2"></i>Password</label>
                        <div class="error-message" id="passwordError">Password must be at least 6 characters.</div>
                    </div>
                    
                    <div class="d-flex justify-content-between align-items-center mt-3 mb-4">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe">
                            <label class="form-check-label" for="rememberMe">
                                Remember me
                            </label>
                        </div>
                        <div>
                            <a href="#" class="forgot-link">Forgot password?</a>
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-primary w-100">
                        <i class="fas fa-sign-in-alt me-2"></i>Login
                    </button>
                </form>
                
                <div class="divider mt-4 mb-3">
                    <span>OR</span>
                </div>
                
                <div class="social-login">
                    <a href="#" class="social-btn facebook">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a href="#" class="social-btn google">
                        <i class="fab fa-google"></i>
                    </a>
                    <a href="#" class="social-btn twitter">
                        <i class="fab fa-twitter"></i>
                    </a>
                </div>
                
                <div class="forgot-password">
                    <a href="#">Forgot your password?</a>
                </div>
            </div>
            <div class="register-link">
                Don't have an account? <a href="register.jsp" id="registerLink">Register Now</a>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Custom JavaScript -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const loginForm = document.getElementById('loginForm');
            const usernameInput = document.getElementById('usernameInput');
            const passwordInput = document.getElementById('passwordInput');
            const usernameError = document.getElementById('usernameError');
            const passwordError = document.getElementById('passwordError');
            const successMessage = document.getElementById('successMessage');
            const registerLink = document.getElementById('registerLink');
            
            // Add animation to form fields when focusing
            const formFields = document.querySelectorAll('.form-control');
            formFields.forEach(field => {
                field.addEventListener('focus', () => {
                    field.parentElement.classList.add('fade-in');
                });
            });
            
            // Username/Email validation with visual feedback
            usernameInput.addEventListener('blur', function() {
                if (usernameInput.value.trim() === '') {
                    usernameInput.classList.add('is-invalid');
                    usernameError.style.display = 'block';
                } else {
                    usernameInput.classList.remove('is-invalid');
                    usernameInput.classList.add('is-valid');
                    usernameError.style.display = 'none';
                }
            });
            
            // Password validation with visual feedback
            passwordInput.addEventListener('blur', function() {
                if (passwordInput.value.length < 6 && passwordInput.value !== '') {
                    passwordInput.classList.add('is-invalid');
                    passwordError.style.display = 'block';
                } else {
                    passwordInput.classList.remove('is-invalid');
                    if (passwordInput.value !== '') {
                        passwordInput.classList.add('is-valid');
                    }
                    passwordError.style.display = 'none';
                }
            });
            
            // Form submission client-side validation
            loginForm.addEventListener('submit', function(event) {
                let isValid = true;
                
                // Validate username/email
                if (usernameInput.value.trim() === '') {
                    usernameInput.classList.add('is-invalid');
                    usernameError.style.display = 'block';
                    isValid = false;
                }
                
                // Validate password
                if (passwordInput.value.length < 6) {
                    passwordInput.classList.add('is-invalid');
                    passwordError.style.display = 'block';
                    isValid = false;
                }
                
                if (!isValid) {
                    // Visual shake animation for invalid form
                    event.preventDefault(); // Prevent form submission
                    loginForm.classList.add('shake');
                    setTimeout(() => {
                        loginForm.classList.remove('shake');
                    }, 500);
                }
            });
            
            // Animation for register link
            registerLink.addEventListener('mouseenter', function() {
                this.style.transform = 'translateY(-3px)';
                this.style.transition = 'transform 0.3s';
            });
            
            registerLink.addEventListener('mouseleave', function() {
                this.style.transform = 'translateY(0)';
            });
            
            // Focus animation for social buttons
            const socialBtns = document.querySelectorAll('.social-btn');
            socialBtns.forEach(btn => {
                btn.addEventListener('mouseenter', () => {
                    socialBtns.forEach(otherBtn => {
                        if (otherBtn !== btn) {
                            otherBtn.style.opacity = '0.5';
                            otherBtn.style.transition = 'opacity 0.3s';
                        }
                    });
                });
                
                btn.addEventListener('mouseleave', () => {
                    socialBtns.forEach(otherBtn => {
                        otherBtn.style.opacity = '1';
                    });
                });
            });
        });
    </script>
</body>
</html>
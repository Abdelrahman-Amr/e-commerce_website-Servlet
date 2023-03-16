<!-- register -->
<div class="register">
	<div class="container">
		<h2>Update Profile</h2>
		<div class="login-form-grids">
			<form method="post" onsubmit="updateProfile(event)">
				<input type="text" placeholder="Name*" value="${customer.userName}" required minlength="3" maxlength="30" id="userName" >
				<input type="date" placeholder="BirthDate*" value=${customer.birthday} required id="birthDate" >
				<input type="tel" placeholder="Phone Number*" value="${customer.phone}" required id="phone" minlength="11" maxlength="11" pattern="^[01][0-9]{10}$" >
				<input type="text" placeholder="Address*" value="${customer.address}" required id="address" minlength="5" maxlength="30">
				<input type="number" placeholder="Credit Limit*" value="${customer.creditLimit}" required id="credit" minlength="3" maxlength="30" min="0" max="50000">
                <input type="submit" value="Update" >
			</form>
		</div>
	</div>
</div>

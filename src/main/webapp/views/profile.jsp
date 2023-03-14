<!-- register -->
<div class="register">
	<div class="container">
		<h2>Update Here</h2>
		<div class="login-form-grids">
			<form method="post" onsubmit="updateProfile(event)">
				<input type="text" value="${customer.userName}" required minlength="3" maxlength="30" id="userName" >
				<input type="date" value=${customer.birthday} required id="birthDate" >
				<input type="text" value="${customer.phone}" required id="phone" minlength="11" maxlength="11" pattern="/^(01)([0|1|5|2]){1}([0-9]{9})$/" >
				<input type="text" value="${customer.address}" required id="address" minlength="5" maxlength="30">
				<input type="number" value="${customer.creditLimit}" required id="credit" minlength="3" maxlength="30" min="0" max="50000">
                <input type="submit" value="Update" >
			</form>
		</div>
	</div>
</div>

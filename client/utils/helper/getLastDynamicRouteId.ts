export function getLastDynamicRouteId() {
	return window.location.href.split('/').pop();
}

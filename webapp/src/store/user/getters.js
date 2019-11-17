export function someGetter(/* state */) {}

export function isAuthenticated(state) {
  return (
    ["executor", "customer", "control", "accounting"].includes(
      state.user.role
    ) && state.user.company.length > 0
  );
}

export function hasCompany(state) {
  return state.user.company.length > 0;
}

export function hasRole(state) {
  return ["executor", "customer", "control", "accounting"].includes(
    state.user.role
  );
}

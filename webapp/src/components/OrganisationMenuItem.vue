<template>
  <q-item clickable @click="click" class="menu-item">
    <q-item-section avatar>
      <q-icon :name="icon" />
    </q-item-section>
    <q-item-section>
      <q-item-label>{{ name }}</q-item-label>
      <q-item-label caption>{{ caption }}</q-item-label>
    </q-item-section>
  </q-item>
</template>

<script>
export default {
  name: "OrganisationMenuItem",
  props: {
    route: String,
    name: String,
    caption: String,
    icon: String
  },
  methods: {
    click() {
      this.$router.push(this.route);
    }
  }
};
</script>

<style lang="stylus">
.q-item {
  transition .4s
  position relative

  &:after {
    position absolute
    content: ''
    width 0%
    bottom 0
    left 0
    background $secondary
    transition .4s
  }

  &:hover {
    background rgba($primary, 0.35)

    &:after {
      width 100%;
      }
    }
}
</style>

<template>
  <q-input bottom-slots :value="value" @input="onInput" :label="label" :placeholder="placeholder" :dense="false">
<!--    <template v-slot:prepend>-->
<!--      <q-icon name="place" />-->
<!--    </template>-->
<!--    <template v-slot:append>-->
<!--      <q-icon name="close" @click="text = ''" class="cursor-pointer" />-->
<!--    </template>-->

<!--    <template v-slot:hint>-->
<!--      Field hint-->
<!--    </template>-->
  </q-input>
</template>

<script>
    export default {
        name: "TextField",
        props: {
            value: String,
            label: String,
            placeholder: String,
        },
        methods: {
            onInput (inp) {
                this.$emit("input", inp)
            }
        }
    }
</script>

<style scoped>

</style>

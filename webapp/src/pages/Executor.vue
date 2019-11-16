<template>
  <q-page padding>
    <q-r
    <div class="block">
      <div class="block__caption">Блок 1</div>
      <text-field label="Номер договора" v-model="number" placeholder="3.14" />
      <text-field label="Дата договора" v-model="date" placeholder="16.11.2019" />
      <text-field label="Сумма договора" v-model="sum" placeholder="1" />
    </div>
    <div class="block">
      <div class="block__caption">Блок 2</div>
      <text-field label="Executor" v-model="executor" placeholder="ООО &quot;Напрастный труд&quot;" />
      <text-field label="Customer" v-model="customer" placeholder="ООО &quot;Заплатим немного&quot;" />
      <text-field label="Control" v-model="control" placeholder="ООО &quot;Нужно переделать&quot;" />
      <text-field label="Accounting" v-model="accounting" placeholder="ООО &quot;Ждите денег&quot;" />
    </div>
  </q-page>
</template>

<script>
import TextField from "../components/Controls/TextField";
export default {
    components: {TextField},

    // name: 'PageName',
    data: () => ({
        number: '0',
        date: '',
        sum: '',

        executor: '',
        customer: '',
        control: '',
        accounting: '',
    })
}
</script>

<style lang="stylus">

.block {
  padding 0 10px;
  margin 10px;

  background-color rgba($primary, .05)

  &__caption {
    padding .5rem
    font-size 1.4rem

  }
}
</style>

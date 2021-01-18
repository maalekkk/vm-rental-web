<template>
<div>
    <p>{{ $t('welcome') }}</p>
    <p>{{ $t('welcome_text') }}</p>
</div>
</template>

<script>
export default {
    beforeMount() {
        this.changeTitle();
    },
    methods: {
        changeTitle() {
            this.$emit('pageTitle', this.$t('main_page'))
        }
    }
}
</script>

<style>
</style>
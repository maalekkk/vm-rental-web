<template>
<div>
    <p>{{ $t('project_name') }}</p>
    <p>{{ $t('description') }}</p>
    <p>{{ $t('authors') }}</p>
</div>
</template>

<script>
export default {
    beforeMount() {
        this.changeTitle();
    },
    methods: {
        changeTitle() {
            this.$emit('pageTitle', this.$t('app_info_title'))
        }
    }
}
</script>

<style>
</style>
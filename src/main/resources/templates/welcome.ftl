<#include 'layout/base.ftl'>

<@baseTemplate
  pageTitle="Random Anime Picker"
>
  <div class="welcome-wrapper">
    <div>
      <h1>Random Anime Picker</h1>
      <p>Can't decide what to watch? Enter your MyAnimeList username below, and we'll pick something for you!</p>
    </div>
    <div class="welcome-inner-wrapper">
      <@forms.htmlForm>
        <@forms.textInput path="form.username" labelText="Username" />
        <@forms.submitButton>Go!</@forms.submitButton>
      </@forms.htmlForm>
    </div>
  </div>
</@baseTemplate>

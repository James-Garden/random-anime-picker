<#include 'layout/base.ftl'>

<@baseTemplate
  pageTitle="Welcome"
>
  <div>
    <h1>Random Anime Picker</h1>
    <p>Can't decide what to watch? Enter you MyAnimeList username below, and we'll pick something for you!</p>
  </div>
  <div style="margin-top: 50px; width: 100%; padding: 50px">
    <@forms.htmlForm>
      <@forms.textInput path="form.username" labelText="Username" />
      <@forms.submitButton />
    </@forms.htmlForm>
  </div>
</@baseTemplate>

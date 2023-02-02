<#-- @ftlvariable name="username" type="String" -->
<#-- @ftlvariable name="listEntry" type="uk.jamesgarden.randomanimepicker.listentry.UserListEntryJson" -->
<#include 'layout/base.ftl'>

<@baseTemplate
  pageTitle="Placeholder Title"
>
  <div>
    Hello world! This will be the list for ${username}
    ${listEntry.animeTitle()!"Missing"} <br>
  </div>
</@baseTemplate>

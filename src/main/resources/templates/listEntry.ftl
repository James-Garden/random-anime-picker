<#-- @ftlvariable name="username" type="String" -->
<#-- @ftlvariable name="listEntry" type="uk.jamesgarden.randomanimepicker.listentry.ListEntry" -->
<#include 'layout/base.ftl'>

<@baseTemplate
  pageTitle="Placeholder Title"
>
  <div>
    <h1>${listEntry.getAnimeTitle()}</h1>
  </div>
</@baseTemplate>

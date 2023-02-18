<#-- @ftlvariable name="username" type="String" -->
<#-- @ftlvariable name="listEntry" type="uk.jamesgarden.randomanimepicker.listentry.ListEntry" -->
<#-- @ftlvariable name="addedToList" type="String" -->

<#include 'layout/base.ftl'>

<#if listEntry.isPresent()>
    <#assign pageTitle=listEntry.get().animeTitleEng!listEntry.get().animeTitle>
<#else>
    <#assign pageTitle="Not found">
</#if>


<@baseTemplate
  pageTitle=pageTitle
>
  <div class="list-entry">
    <div class="list-update">
      <span class="list-update__last-updated">
        Last updated ${lastUpdated}
      </span>
      <span class="list-update__update-now">
        <@forms.htmlForm actionUrl=updateListUrl>
          <#if isListUpdatable>
            <@forms.submitButton class="secondary-link" id="refreshListLink">
              Refresh list
            </@forms.submitButton>
          </#if>
        </@forms.htmlForm>
      </span>
    </div>
    <#if listEntry.isPresent()>
      <@listEntryContent listEntry=listEntry.get() />
    <#else>
      <div class="list-entry-inner-wrapper">
        <h1>Could not find matching entries</h1>
      </div>
    </#if>
  </div>
</@baseTemplate>

<#macro listEntryContent listEntry>
<#-- @ftlvariable name="listEntry" type="uk.jamesgarden.randomanimepicker.listentry.ListEntry" -->
  <div class="list-entry-inner-wrapper">
    <div class="anime-image">
      <@image.image
        imageUrl=listEntry.animeImagePath
        altText=listEntry.animeTitle!"Unknown"
        class="anime-image"
      />
    </div>
    <div class="anime-info">
      <#if listEntry.animeTitleEng?has_content>
        <div class="anime-title">
          <h1>${listEntry.animeTitleEng}</h1>
        </div>
        <div class="anime-title-jp">
          <h2>${listEntry.animeTitle}</h2>
        </div>
      <#else>
        <div class="anime-title-jp">
          <h1>${listEntry.animeTitle}</h1>
        </div>
      </#if>
      <@table.table class="anime-details">
        <@table.simpleRow keyText="Status" valueText=listEntry.status.displayName />
        <@table.simpleRow keyText="Score" valueText=listEntry.animeScoreVal />
        <@table.simpleRow keyText="Number of Episodes" valueText=listEntry.animeNumEpisodes />
        <@table.simpleRow keyText="Date Added to List" valueText=addedToList!"" />
        <@table.simpleRow keyText="Airing Status" valueText=listEntry.animeAiringStatus.displayName />
        <@table.simpleRow keyText="Age Rating" valueText=listEntry.animeMpaaRatingString!"" />
      </@table.table>
    </div>
  </div>
  <div class="new-random-entry-form">
    <@forms.htmlForm method="get">
      <@forms.submitButton preventDoubleClick=false>Go!</@forms.submitButton>
    </@forms.htmlForm>
  </div>
  <div class="my-anime-list-link">
    <a href="https://myanimelist.net${listEntry.animeUrl}" class="secondary-link">View on MyAnimeList</a>
  </div>
</#macro>

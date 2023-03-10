<#-- @ftlvariable name="username" type="String" -->
<#-- @ftlvariable name="listEntry" type="uk.jamesgarden.randomanimepicker.listentry.ListEntry" -->
<#-- @ftlvariable name="addedToList" type="String" -->

<#include 'layout/base.ftl'>

<#if listEntry.isPresent()>
  <#if listEntry.get().animeTitleEnglish?has_content>
    <#assign pageTitle=listEntry.get().animeTitleEnglish>
  <#elseif listEntry.get().animeTitle?has_content>
    <#assign pageTitle=listEntry.get().animeTitle>
  <#elseif listEntry.get().animeTitleJapanese?has_content>
    <#assign pageTitle=listEntry.get().animeTitleJapanese>
  <#else>
    <#assign pageTitle="Missing title">
  </#if>
</#if>


<@baseTemplate
  pageTitle=pageTitle
>
  <div class="list-entry">
    <div class="back-link">
      <span class="secondary-text">&lt; </span><a href="/" class="secondary-link">Back</a>
    </div>
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
        imageUrl=listEntry.animeImage
        altText=listEntry.animeTitle!"Unknown"
        class="anime-image"
      />
    </div>
    <div class="anime-info">
      <#if listEntry.animeTitleEnglish?has_content>
        <div class="anime-title">
          <h1>${listEntry.animeTitleEnglish}</h1>
        </div>
        <#if listEntry.animeTitleJapanese?has_content && (listEntry.animeTitleJapanese != listEntry.animeTitleEnglish)>
          <div class="anime-title-jp">
            <h2>${listEntry.animeTitleJapanese}</h2>
          </div>
        </#if>
      <#else>
        <div class="anime-title">
          <h1>${listEntry.animeTitle}</h1>
        </div>
      </#if>
      <@table.table class="anime-details">
        <@table.simpleRow keyText="Status" valueText=listEntry.listEntryStatus.displayName />
        <@table.simpleRow keyText="Score" valueText=listEntry.animeAverageScore!"" />
        <@table.simpleRow keyText="Number of Episodes" valueText=listEntry.animeNumEpisodes!"" />
        <@table.simpleRow keyText="Date Added to List" valueText=addedToList!"" />
        <@table.simpleRow keyText="Airing Status" valueText=listEntry.airingStatus.displayName />
        <@table.simpleRow keyText="Age Rating" valueText=listEntry.ageRating.displayName />
      </@table.table>
    </div>
  </div>
  <div class="new-random-entry-form">
    <@forms.htmlForm method="get">
      <@forms.submitButton preventDoubleClick=true>Go!</@forms.submitButton>
    </@forms.htmlForm>
  </div>
  <#if listEntry.animeId?has_content>
    <div class="my-anime-list-link">
      <a href="https://myanimelist.net/anime/${listEntry.animeId?c}" class="secondary-link">View on MyAnimeList</a>
    </div>
  </#if>
</#macro>

<#-- @ftlvariable name="randomEntry" type="uk.jamesgarden.randomanimepicker.randomentry.RandomEntryView" -->

<#include 'layout/base.ftl'>

<#if randomEntry.englishTitle()?has_content>
  <#assign pageTitle=randomEntry.englishTitle()>
<#elseif randomEntry.animeTitle()?has_content>
  <#assign pageTitle=randomEntry.animeTitle()>
<#elseif randomEntry.japaneseTitle()?has_content>
  <#assign pageTitle=randomEntry.japaneseTitle()>
<#else>
  <#assign pageTitle="Random Anime Picker">
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
    <#if !randomEntry.isEmpty()>
      <@listEntryContent randomEntry=randomEntry />
    <#else>
      <div class="list-entry-inner-wrapper">
        <h1>Could not find matching entries</h1>
      </div>
    </#if>
  </div>
</@baseTemplate>

<#macro listEntryContent randomEntry>
<#-- @ftlvariable name="randomEntry" type="uk.jamesgarden.randomanimepicker.randomentry.RandomEntryView" -->
  <div class="list-entry-inner-wrapper">
    <div class="anime-image">
      <@image.image
        imageUrl=randomEntry.animeImageUrl()!"https://via.placeholder.com/350x450.png?text=NOT%20FOUND"
        altText=randomEntry.animeTitle()!"Image not found"
        class="anime-image"
      />
    </div>
    <div class="anime-info">
      <#if randomEntry.englishTitle()?has_content>
        <div class="anime-title">
          <h1>${randomEntry.englishTitle()}</h1>
        </div>
        <#if randomEntry.japaneseTitle()?has_content && (randomEntry.japaneseTitle() != randomEntry.englishTitle())>
          <div class="anime-title-jp">
            <h2>${randomEntry.japaneseTitle()}</h2>
          </div>
        </#if>
      <#else>
        <div class="anime-title">
          <h1>${randomEntry.animeTitle()!"Title not found"}</h1>
        </div>
      </#if>
      <@table.table class="anime-details">
        <@table.simpleRow keyText="Status" valueText=randomEntry.watchingStatus()!"" />
        <@table.simpleRow keyText="Score" valueText=randomEntry.communityScore()!"" />
        <@table.simpleRow keyText="Number of Episodes" valueText=randomEntry.numberOfEpisodes()!"" />
        <@table.simpleRow keyText="Date Added to List" valueText=randomEntry.dateAddedToList()!"" />
        <@table.simpleRow keyText="Airing Status" valueText=randomEntry.airingStatus()!"" />
        <@table.simpleRow keyText="Age Rating" valueText=randomEntry.ageRating()!"" />
      </@table.table>
    </div>
  </div>
  <div class="new-random-entry-form">
    <@forms.htmlForm method="get">
      <@forms.submitButton preventDoubleClick=true>Go!</@forms.submitButton>
    </@forms.htmlForm>
  </div>
  <#if randomEntry.animeId()?has_content>
    <div class="my-anime-list-link">
      <a href="https://myanimelist.net/anime/${randomEntry.animeId()?c}" class="secondary-link">View on MyAnimeList</a>
    </div>
  </#if>
</#macro>
